package core.fw.controller;

import org.springframework.web.servlet.mvc.AbstractController;

import core.fw.controller.vo.CommonDataVo;

public abstract class AbstractFwController extends AbstractController{
	
	private static CommonDataVo commonDataVo;

	protected CommonDataVo getCommonDataVo() {
		return commonDataVo;
	}

	protected void setCommonDataVo(CommonDataVo commonDataVo) {
		this.commonDataVo = commonDataVo;
	}
	
	

}
