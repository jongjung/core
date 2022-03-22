package core.fw.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@SuppressWarnings("static-access")
@Service
public class SpringFactoryLoader implements ApplicationContextAware {

	private static ApplicationContext context;
	
	public static ApplicationContext getContext() {
		return context;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}
}
