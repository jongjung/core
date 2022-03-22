package core.fw.util;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SpringBeanUtils {

	public static Object getBean(ServletContext servletContext, String beanId) {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		Object beanObject = context.getBean(beanId);
		return beanObject;
	}
	
	public static Object getBean(ServletContext servletContext, Class<?> requiredType) {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		Object beanObject = context.getBean(requiredType);
		return beanObject;
	}
	
	
}
