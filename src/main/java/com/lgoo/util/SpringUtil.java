package com.lgoo.util;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Spring Utils
 * @author Lee
 *
 */
@Service
public class SpringUtil implements ApplicationContextAware {
	
	private static ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "spring/spring-global.xml" }); ;
	
	@Override
	public void setApplicationContext(ApplicationContext contex)
	   throws BeansException {
		if(SpringUtil.context == null) {
			SpringUtil.context=contex;
		}
	}
	
	public static ApplicationContext getContext(){
	  return context;
	}	
	
	public static Object getBean(String name){  
        return context.getBean(name);  
    }  
}