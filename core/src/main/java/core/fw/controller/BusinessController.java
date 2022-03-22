package core.fw.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.async.WebAsyncManager;
import org.springframework.web.context.request.async.WebAsyncUtils;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.util.NestedServletException;

import com.google.gson.Gson;

import core.biz.dps.mm.vo.AccountTransCondVo;
import core.biz.dps.mm.vo.AccountTransHisVo;
import core.fw.controller.vo.CommonDataVo;
import core.fw.util.JsonUtil;
import core.fw.util.ServiceCacheUtil;
import core.fw.util.SpringBeanUtils;
import core.fw.util.SpringFactoryUtil;
import core.fw.util.vo.ServiceVo;

@Component("businessController")	
public class BusinessController  extends AbstractFwController{
	
	private static final Logger logger = LoggerFactory.getLogger(BusinessController.class);
	
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
		throws Exception {
			
		   String bizData = (String) request.getAttribute("bizData");
		   logger.debug("business Text : " + bizData);
		   ModelAndView mv = new ModelAndView();
		   //Map paramMap = new HashMap<String, Object>();
		   
		   //TO-DO BIZ 서비스 호출 		   
		   try {
		   
			   Object result = invokeMethod(bizData);
		      //result 결과 
			
			   String resultStr = JsonUtil.getGsonBasic().toJson(result);
			   
			   logger.debug("result json : " + resultStr);
			
			   //paramMap.put("bizDataOut", result);
			   mv.setViewName("forward:/outBoundController");
			   mv.addObject("bizDataOut", result);
			   
		   }catch(Exception e) {
			   e.printStackTrace();
		   }
		   
		   
		// TODO OutBoundControler 호출
		   return mv;
	}

	protected Object invokeMethod(String bizData) {
		// TODO Auto-generated method stub
		
		  logger.debug("service_code : " + super.getCommonDataVo().getService_code());
		
		   ServiceVo serviceVo = ServiceCacheUtil.getInstance().getServiceSpec(super.getCommonDataVo().getService_code());			
		   
		   String targetClassName = serviceVo.getClassName();
		   String targetMethodName = serviceVo.getMethodName();
		   String targetParamClassName = serviceVo.getInputVoClassName();
		   String targetResultClassName = serviceVo.getOutputVoClassName();
		   
		   Object objClassBean = (Object)SpringFactoryUtil.getBean(targetClassName);		  
		   logger.debug("objClassBean.toString() : " + objClassBean.toString());
		   
		   try {
			  			     
			   
			   Object paramClassObject = bindPamamObject(targetParamClassName,bizData);
			   Method method = objClassBean.getClass().getMethod(targetMethodName, paramClassObject.getClass());
					   
			   logger.debug("method : " + method.getName());
			   
			   Class<?> resultClass = Class.forName(targetResultClassName);
			   
			   Object result =  method.invoke(objClassBean, paramClassObject);
				
			   //logger.debug("sucess 1: " + result.getAccountNo());
			   //logger.debug("sucess 2: " + result.getCustomId());
			   //logger.debug("sucess 3: " + result.getTransDate());
			   
				return result;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 	
		   
		   return null;
		   
	}
	
	private Object bindPamamObject(String targetParamClassName, String bizData) {
		// TODO Auto-generated method stub
		
		try {
			Class<?> paramClass = Class.forName(targetParamClassName);
			logger.debug("bindPamamObject : " + paramClass.toString());
			
			//Object paramClassInstance = paramClass.newInstance();
			
			
			Object paramClassInstance = JsonUtil.getInstance().getGsonBasic().fromJson(bizData, paramClass);
			  
			//Object paramClassInstance2 = gson.fromJson(bizData, paramClassInstance.getClass());
			  
			return paramClassInstance; 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		return null;
	}

	private Method findMethod(Object bean, String methodName) throws Exception{
	
		Method[] methods = bean.getClass().getMethods();
	  
	      for(int i = 0 ; i < methods.length ; i++) {
	    	  if( methods[i].getName().equals(methodName)) {
	    		  return methods[i];
	    	  }
	      }
	
	    return null;
	}
}


