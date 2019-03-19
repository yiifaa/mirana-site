package com.yiifaa.mirana.commons.repository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.yiifaa.mirana.commons.domain.Account;
import com.yiifaa.mirana.persistence.GenericRepository;

/**
 * 
 * 
 * @author <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version 1.0 开发日期：2017年6月2日 ： 上午11:36:46
 */
@Repository("accountRepository")
public interface AccountRepository extends GenericRepository<Account, String> {

	/**
	 * 载入唯一的用户，忽略大小写
	 * 
	 * @param username
	 * @return
	 */
	UserDetails findByUsernameIgnoreCase(String username);
	
	/**
	 * 
	 * @param username
	 * @return
	 */
	Account findByUsername(String username);

}
