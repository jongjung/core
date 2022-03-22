package core.fw.util;

import java.util.HashMap;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import core.fw.util.vo.ServiceVo;

public class JsonUtil {

	private static JsonUtil sInstance;
	
	private static Gson gsonBasic;
	
	private static Gson gsonUnderscores;
	
	public synchronized static JsonUtil getInstance() {
		if(sInstance == null) {
			sInstance = new JsonUtil();
			initalize();
		}
		return sInstance;
	}
	
	private static void initalize() {
		// TODO Auto-generated method stub
		gsonBasic = new Gson();
		gsonUnderscores = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
		
	}

	public static Gson getGsonBasic() {
		return gsonBasic;
	}

	public static Gson getGsonUnderscores() {
		return gsonUnderscores;
	}
	
	
}
