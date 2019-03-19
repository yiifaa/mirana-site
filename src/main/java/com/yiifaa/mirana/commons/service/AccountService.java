package com.yiifaa.mirana.commons.service;

import org.springframework.data.domain.Page;

import com.yiifaa.mirana.commons.AccountType;
import com.yiifaa.mirana.commons.domain.Account;
import com.yiifaa.mirana.commons.query.AccountQuery;
import com.yiifaa.mirana.persistence.GenericService;

/**
 * 
 * 
 * @author <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version 1.0 开发日期：2017年6月2日 ： 下午2:26:10
 */
public interface AccountService extends GenericService<Account, String> {

	Page<Account> find(AccountQuery pageQuery);
	
	/**
	 * 
	 * @param account
	 * @param type
	 * @return
	 */
	Account persistByType(Account account, AccountType type);
	
	/**
	 * 
	 * @param username
	 * @return
	 */
	Account findByUsername(String username);
	
}
