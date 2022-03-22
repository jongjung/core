package core.fw.util;

import java.util.HashMap;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;

import core.fw.util.vo.ServiceVo;

public class ServiceCacheUtil {

	private static ServiceCacheUtil sInstance;
	
	private static HashMap<String, Object> serviceMap; 
	
	public synchronized static ServiceCacheUtil getInstance() {
		if(sInstance == null) {
			sInstance = new ServiceCacheUtil();
			initalize();
		}
		return sInstance;
	}
	
	private static void initalize() {
		// TODO Auto-generated method stub
		serviceMap = new HashMap<String, Object>();		
		ServiceVo service1 = new ServiceVo();		
		service1.setClassName("core.biz.dps.mm.bc.impl.AccountBcImpl");
		service1.setMethodName("retrieveAccountTransHistory");
		service1.setInputVoClassName("core.biz.dps.mm.vo.AccountTransCondVo");
		service1.setOutputVoClassName("core.biz.dps.mm.vo.AccountTransHisVo");
		
		ServiceVo service2 = new ServiceVo();		
		service2.setClassName("core.biz.dps.mm.bc.impl.AccountBcImpl");
		service2.setMethodName("retrieveAccountDetail");
		service2.setInputVoClassName("core.biz.dps.mm.vo.AccountTransCondVo");
		service2.setOutputVoClassName("core.biz.dps.mm.vo.AccountDetailVo");
		
		ServiceVo service3 = new ServiceVo();		
		service3.setClassName("core.biz.lon.ll.bc.impl.LonAccountBcImpl");
		service3.setMethodName("retrieveLonAccountDetail");
		service3.setInputVoClassName("LonAccountCondVo");
		service3.setOutputVoClassName("LonAccountDetailVo");
		
		serviceMap.put("SDPSMM0001", service1);
		serviceMap.put("SDPSMM0002", service2);		
		serviceMap.put("SLONLL0001", service3);
		
	}

	public ServiceVo getServiceSpec(String serviceName) {
		
		return (ServiceVo) serviceMap.get(serviceName);
	}
	
	
	
}
