package com.yiifaa.mirana.commons.query;

import com.yiifaa.mirana.commons.domain.Role;
import com.yiifaa.mirana.persistence.query.PageQuery;

/**
 * 
 * 
 * @author <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version 1.0 开发日期：2017年6月7日 ： 上午11:47:50
 */
public class RoleQuery extends PageQuery<Role> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cnitsec.mirana.query.PageQuery#initSearchForm()
	 */
	@Override
	protected Role initSearchForm() {
		return new Role();
	}

}
