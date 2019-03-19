package com.yiifaa.mirana.commons.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.yiifaa.mirana.commons.AccountType;
import com.yiifaa.mirana.commons.domain.Role;
import com.yiifaa.mirana.commons.query.RoleQuery;
import com.yiifaa.mirana.persistence.GenericService;


/**
 * 
 * 
 * @author <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version 1.0 开发日期：2017年6月7日 ： 上午10:23:24
 */
public interface RoleService extends GenericService<Role, String> {

	Page<Role> find(RoleQuery query);
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	Role findByName(AccountType type);

}
