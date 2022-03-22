package core.fw.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import core.fw.controller.vo.CommonDataVo;

@Component("outBoundController")
public class OutBoundController extends AbstractFwController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		 //String bizDataOut = (String) request.getAttribute("bizDataOut");
		 //logger.debug("business Text : " + bizDataOut);
		 Object obj = request.getAttribute("bizDataOut");
		 
		 
		 ModelAndView mv = new ModelAndView();
		 //Map paramMap = new HashMap<String, Object>();	
		 
		 CommonDataVo commonDataVo= super.getCommonDataVo();
		 
		 try {
			 

			 commonDataVo.setRequestType("R");
			 commonDataVo.setResponseType("NM");
			 //mv.setViewName("smartView");
			 mv.setViewName("jsonView");
			 mv.addObject("commonData",commonDataVo);
			 mv.addObject("bizDataOut", obj);
			 
			 
		 }catch(Exception e){
			// mv.setViewName("smartErrorView");
			 
			 commonDataVo.setRequestType("R");
			 commonDataVo.setResponseType("ER");
			 //mv.setViewName("smartView");
			 mv.setViewName("jsonView");
			 mv.addObject("commonData",commonDataVo);
			// mv.addObject("bizDataErr", );
			 
			 
		 }
		
		   
			   
		   
		 return mv;
	}
		
	
	
}
