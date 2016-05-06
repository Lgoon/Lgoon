package com.lgoo.service.impl;


import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.lgoo.service.DispatchService;
import com.lgoo.util.Cmd;
import com.lgoo.util.NettyService;
import com.lgoo.util.SpringUtil;

@NettyService(sid = 0)
public class DispatchServiceImpl implements DispatchService {
	
	private static Map cmdMap = new HashMap();
	
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
		Resource[] res = rpr.getResources("/com/lgoo/service/impl/*");
		Enumeration<URL> url = DispatchServiceImpl.class.getClassLoader().getSystemResources("*");
		System.out.println("url==>" + url.hasMoreElements());
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
	            if(aType == NettyService.class){
	            	NettyService service = (NettyService)annotation;
	            	
	            	Method[] methods = clazz.getMethods();
	            	
	            	for(Method method : methods) {
	            		
	            		Annotation[] mannos = method.getAnnotations();
	            		
	            		for (Annotation manno : mannos) {
	            			Class<? extends Annotation> mType = manno.annotationType();
		            		if (mType == Cmd.class){
		     	            	Cmd cmd = (Cmd)manno;
		     	            	cmdMap.put(cmd.cid(), new Object[] {clazz,method});
		     	            }
	            		}
	            	}
	            }
	        }
		}
	}
	
	@Override
	public Object dispatch(short cid,byte[] args) throws Exception {
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
