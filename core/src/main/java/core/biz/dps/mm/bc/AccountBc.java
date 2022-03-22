package core.biz.dps.mm.bc;

import core.biz.dps.mm.vo.AccountDetailVo;
import core.biz.dps.mm.vo.AccountTransCondVo;
import core.biz.dps.mm.vo.AccountTransHisVo;

public interface AccountBc {

	public AccountTransHisVo retrieveAccountTransHistory(AccountTransCondVo vo);
	
	public AccountDetailVo retrieveAccountDetail(AccountTransCondVo vo);
}
