package core.fw.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import core.fw.controller.vo.CommonDataVo;
import core.fw.util.JsonUtil;


@Controller
public class InBoundController extends AbstractFwController{
	
	private static final Logger logger = LoggerFactory.getLogger(InBoundController.class);
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		   // TODO Auto-generated method stub
		   String telegraphText = null; 
		   //logger.debug("plainText : " + telegraphText);
		
		   ModelAndView mv = new ModelAndView();
		   //Map paramMap = new HashMap<String, Object>();
		   
		   telegraphText= this.getBody(request);
		   // TODO 전문파싱 하는부분 추가(commonData / bizData)
		   
		   HashMap<String, Object> originData = JsonUtil.getInstance().getGsonBasic().fromJson(telegraphText, new HashMap<String, Object>().getClass());
		   
		   String commData = originData.get("commonData").toString();
		   String bizData =  originData.get("bizData").toString();	   

		   // TODO 공통부 파싱   (controller에서 사용하가능하도록 set)
		   CommonDataVo commonDataVo = JsonUtil.getInstance().getGsonUnderscores().fromJson(commData, new CommonDataVo().getClass());
		   
		   logger.debug("commData : " + commData);
		   logger.debug("bizData : " + bizData);
		   
		   // TODO 입력데이터 로깅 / DB에 데에터 저장 (transaction관리) / 저널로그  
		
		   //junallog(commonDataVo.getGuid(),commData, bizData);
		   
		   // TODO 공통부 controller에서 사용하가능하도록 set
		   super.setCommonDataVo(commonDataVo);
		  
		   logger.debug("============commonData============================");
		   logger.debug(commonDataVo.getGuid());
		   logger.debug(commonDataVo.getRequestType());
		   logger.debug(commonDataVo.getResponseType());
		   logger.debug(commonDataVo.getService_code());
		   logger.debug(commonDataVo.getSystemCode());
		   logger.debug(commonDataVo.getTrxDatetime());
		   logger.debug("========================================");
		   
		   logger.debug("==============super.getCommonDataVo()==========================");
		   logger.debug(super.getCommonDataVo().getGuid());
		   logger.debug(super.getCommonDataVo().getRequestType());
		   logger.debug(super.getCommonDataVo().getResponseType());
		   logger.debug(super.getCommonDataVo().getService_code());
		   logger.debug(super.getCommonDataVo().getSystemCode());
		   logger.debug(super.getCommonDataVo().getTrxDatetime());
		   logger.debug("========================================");
		  
		   
		   // TODO BusinessControler 호출 
		   		   
		   mv.setViewName("forward:/businessController");		  		   
		   mv.addObject("bizData", bizData);
		   
		   
		   return mv;
		    
	}
	
	
	public static String getBody(HttpServletRequest request) throws IOException {
		 
        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
 
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
 
        body = stringBuilder.toString();
        return body;
    }
	
	
}
