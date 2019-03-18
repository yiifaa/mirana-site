package com.yiifaa.mirana.commons.query;

import com.yiifaa.mirana.commons.domain.Account;
import com.yiifaa.mirana.persistence.query.PageQuery;

/**
 * 
 * 
 * @author <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version 1.0 开发日期：2017年6月2日 ： 下午6:14:39
 */
public class AccountQuery extends PageQuery<Account> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cnitsec.cloud.query.PageQuery#initSearchForm()
	 */
	@Override
	protected Account initSearchForm() {
		return new Account();
	}

}
