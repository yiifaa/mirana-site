package com.yiifaa.mirana.commons.service.impl;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableMap.Builder;
import com.yiifaa.mirana.commons.AccountType;
import com.yiifaa.mirana.commons.domain.Role;
import com.yiifaa.mirana.commons.query.RoleQuery;
import com.yiifaa.mirana.commons.repository.RoleRepository;
import com.yiifaa.mirana.commons.service.RoleService;
import com.yiifaa.mirana.persistence.GenericRepository;
import com.yiifaa.mirana.persistence.query.PageQueryBuilder;
import com.yiifaa.mirana.persistence.query.QueryUtil;

/**
 * 
 * 
 * @author <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version 1.0 开发日期：2017年6月7日 ： 上午10:24:00
 */
@Service
public class RoleServiceImpl implements RoleService {

	private RoleRepository roleRepository;

	/**
	 * @param roleRepository
	 */
	@Inject
	public RoleServiceImpl(RoleRepository roleRepository) {
		super();
		this.roleRepository = roleRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cnitsec.mirana.persistence.GenericService#getRepository()
	 */
	@Override
	public GenericRepository<Role, String> getRepository() {
		return roleRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cnitsec.mirana.security.service.RoleService#find(com.cnitsec.mirana.
	 * security.query.RoleQuery)
	 */
	@Override
	public Page<Role> find(RoleQuery pageQuery) {
		return this.find(pageQuery, new PageQueryBuilder<Role>() {

			@Override
			public String buildSelect() {
				return "select role from Role role ";
			}

			@Override
			public String buildWhere(Role s, Builder<String, Object> params) {
				StringBuilder builder = new StringBuilder();
				String name = s.getName();
				if (StringUtils.isNotBlank(name)) {
					builder.append(" and role.name like :name");
					params.put("name", QueryUtil.buildLike(name));
				}
				return builder.toString();
			}

			@Override
			public String buildBys(String column, String order) {
				return QueryUtil.buildOrderBy("role", column, order);
			}

			@Override
			public String buildCount() {
				return "select count(role) from Role role ";
			}
		});
	}

	@Override
	public Role findByName(AccountType type) {
		String name = type.name();
		List<Role> items = this.roleRepository.findByName(name);
		if(items.size() > 0) {
			return items.get(0);
		}
		return null;
	}

}
