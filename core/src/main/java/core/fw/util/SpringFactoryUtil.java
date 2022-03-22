package core.fw.util;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;

public class SpringFactoryUtil {

	private static SpringFactoryUtil sInstance;
	
	public synchronized static SpringFactoryUtil getInstance() {
		if(sInstance == null) {
			sInstance = new SpringFactoryUtil();
		}
		return sInstance;
	}
	
	public static Object getBean(String beanName) {
		try {
			Object rtnBean = SpringFactoryLoader.getContext().getBean(beanName);
			return rtnBean;
		}catch (Exception e) {
			throw e;
		}
		
	}
	
	public static <T> T getBean(Class<T> paramClass) {
		try {
			T rtnBean = SpringFactoryLoader.getContext().getBean(paramClass);
			return rtnBean;
		} catch (NoUniqueBeanDefinitionException e) {
			try {
				String infName = paramClass.getSimpleName();
				String serviceBeanId = infName.substring(0,1).toLowerCase() + infName.substring(1);
				T rtnBean = SpringFactoryLoader.getContext().getBean(serviceBeanId, paramClass);
				return rtnBean;
			}catch(Exception se) {
				throw e;
			}
		} catch (NoSuchBeanDefinitionException e) {
			try {
				String infName = paramClass.getSimpleName().replace("Impl", "");
				String serviceBeanId = infName.substring(0,1).toLowerCase() + infName.substring(1);
				T rtnBean = SpringFactoryLoader.getContext().getBean(serviceBeanId, paramClass);
				@SuppressWarnings("unchecked")
				T obj = (T)rtnBean;
				return obj;
			}catch(Exception se) {
				throw se;
			}
			
		} catch (Exception e) {
			throw e;
		}
	}
		
	public static <T> T getBean(String beanName, Class<T> paramClass) {
		try {
			T rtnBean = SpringFactoryLoader.getContext().getBean(beanName, paramClass);
			return rtnBean;
		}catch (Exception e) {
			throw e;
		}
		
	}
	
}
