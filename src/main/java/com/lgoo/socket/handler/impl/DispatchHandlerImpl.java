package com.lgoo.socket.handler.impl;


import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.lgoo.service.DispatchService;
import com.lgoo.socket.handler.DispatchHandler;
import com.lgoo.util.Cmd;
import com.lgoo.util.SocketService;

@SocketService(sid = 0x0000)
public class DispatchHandlerImpl implements DispatchHandler {
	
	private static Map<Short,Object[]> cmdMap = new HashMap<Short,Object[]>();
	private static Map<Short,Map<Short,Object[]>> sidMap = new HashMap<Short,Map<Short,Object[]>>();
	
	static {
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void init() throws Exception  {
		ResourcePatternResolver rpr = new PathMatchingResourcePatternResolver();
		//com\lgoo\service\impl
		Resource[] res = rpr.getResources("/com/lgoo/socket/handler/impl/*");
		
		System.out.println("res==>" + res.length);
		
		String[] clazzs = new String[res.length];
		
		for (int i = 0; i < clazzs.length; i++) {
			String className = res[i].getURL().getPath();
			System.out.println(className);
			try {
			className = className.split("(classes/)|(!/)")[1];
			className = className.replace("/", ".").replace(".class", "");
			} catch (Exception e) {
				
			}
			clazzs[i] = className;
			Class clazz;
			try {
				clazz = ClassLoader.getSystemClassLoader().loadClass(clazzs[i]);
			} catch (ClassNotFoundException e) {
				continue;
			}
			Annotation[] annotations = clazz.getAnnotations();
	        for(Annotation annotation : annotations){
	        	Class<? extends Annotation> aType = annotation.annotationType();
	            if(aType == SocketService.class){
	            	SocketService service = (SocketService)annotation;
	            	
	            	Method[] methods = clazz.getMethods();
	            	Map<Short,Object[]> methodMap = new HashMap<Short,Object[]>();
	            	for(Method method : methods) {
	            		
	            		Annotation[] mannos = method.getAnnotations();
	            		
	            		for (Annotation manno : mannos) {
	            			Class<? extends Annotation> mType = manno.annotationType();
		            		if (mType == Cmd.class){
		     	            	Cmd cmd = (Cmd)manno;
		     	            	cmdMap.put(cmd.cid(), new Object[] {clazz,method});
		     	            	methodMap.put(cmd.cid(), new Object[] {clazz,method});
		     	            }
	            		}
	            	}
	            	
	            	sidMap.put(service.sid(), methodMap);
	            	
	            }
	        }
		}
	}
	
	@Override
	public Object dispatch(short cid,Object... args) throws Exception {
		Object[] patch = (Object[]) cmdMap.get(cid);
		if (patch != null) {
			Class clazz = (Class) patch[0];
			Method method = (Method) patch[1];
			
			Object o = clazz.newInstance();
			return method.invoke(o, args);
		} else {
			return null;
		}
	 }

	@Override
	public Object dispatch(short sid, short cid, Object... args)
			throws Exception {
		Object[] patch = (Object[]) cmdMap.get(cid);
		if (patch != null) {
			Class clazz = (Class) patch[0];
			Method method = (Method) patch[1];
			
			Object o = clazz.newInstance();
			return method.invoke(o, args);
		} else {
			return null;
		}
	}
	
	
	
}
