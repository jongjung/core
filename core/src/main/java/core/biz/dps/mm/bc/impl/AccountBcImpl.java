package core.biz.dps.mm.bc.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import core.biz.dps.mm.bc.AccountBc;
import core.biz.dps.mm.vo.AccountDetailVo;
import core.biz.dps.mm.vo.AccountTransCondVo;
import core.biz.dps.mm.vo.AccountTransHisVo;
import core.fw.controller.BusinessController;

@Component("core.biz.dps.mm.bc.impl.AccountBcImpl")
public class AccountBcImpl implements AccountBc {

		private static final Logger logger = LoggerFactory.getLogger(AccountBcImpl.class);
	
		public AccountTransHisVo retrieveAccountTransHistory(AccountTransCondVo inputData){
	
			AccountTransHisVo result= new AccountTransHisVo();			
			logger.debug("retrieveAccountTransHistory pass");			
			if("11111".equals(inputData.getCustomId()) && "1234-5678-22".equals(inputData.getAccountNo())){
				result.setCustomId("11111");
				result.setAccountNo("1234-5678-22");
				result.setTransDate("2022-05-01");				
			}else {
				result.setCustomId("0000");
				result.setAccountNo("0000-0000-00");
				result.setTransDate("0000-00-00");				
			}			
			return result;			
		}
		
		public AccountDetailVo retrieveAccountDetail(AccountTransCondVo inputData) {
			
			AccountDetailVo result= new AccountDetailVo();			
			logger.debug("retrieveAccountDetail pass");			
			
			if("1234-5678-22".equals(inputData.getAccountNo())){
				result.setCustomId("11111");
				result.setAccountNo("1234-5678-22");
				result.setCreationDate("2022-05-01");
				result.setBranchNo("111111");
			}else {
				result.setCustomId("00000");
				result.setAccountNo("0000-0000-00");
				result.setCreationDate("2021-05-01");
				result.setBranchNo("000000");				
			}			
			return result;			
			
			
		};
		
		
}
