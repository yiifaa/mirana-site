package com.yiifaa.mirana.commons.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableMap.Builder;
import com.yiifaa.mirana.commons.domain.Account;
import com.yiifaa.mirana.commons.domain.Role;
import com.yiifaa.mirana.commons.query.AccountQuery;
import com.yiifaa.mirana.commons.repository.AccountRepository;
import com.yiifaa.mirana.commons.service.AccountService;
import com.yiifaa.mirana.persistence.GenericRepository;
import com.yiifaa.mirana.persistence.query.PageQueryBuilder;
import com.yiifaa.mirana.persistence.query.QueryUtil;

/**
 * 
 * 
 * @author <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version 1.0 开发日期：2017年6月4日 ： 下午9:00:38
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService, UserDetailsService {

	private AccountRepository accountDao;

	private PasswordEncoder passwordEncoder;
	
	/**
	 * @param accountDao
	 */
	@Inject
	public AccountServiceImpl(AccountRepository accountDao, PasswordEncoder passwordEncoder) {
		super();		
		this.accountDao = accountDao;
		this.passwordEncoder = passwordEncoder;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cnitsec.mirana.persistence.GenericService#getRepository()
	 */
	@Override
	public GenericRepository<Account, String> getRepository() {
		return accountDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cnitsec.mirana.security.service.AccountService#find(com.cnitsec.
	 * mirana.security.query.AccountQuery)
	 */
	@Override
	public Page<Account> find(AccountQuery pageQuery) {
		return this.find(pageQuery, new PageQueryBuilder<Account>() {

			@Override
			public String buildSelect() {
				return "select account from Account account ";
			}

			@Override
			public String buildWhere(Account s, Builder<String, Object> params) {
				StringBuilder sql = new StringBuilder();
				String username = s.getUsername();
				if (StringUtils.isNotBlank(username)) {
					sql.append(" and account.username like :username");
					params.put("username", QueryUtil.buildLike(username));
				}
				return sql.toString();
			}

			@Override
			public String buildBys(String column, String order) {
				return QueryUtil.buildOrderBy("account", column, order);
			}

			@Override
			public String buildCount() {
				return "select count(account) from Account account ";
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cnitsec.mirana.persistence.GenericService#save(com.cnitsec.mirana.
	 * persistence.Identifiable)
	 */
	@Override
	@Transactional(readOnly = false)
	public <S extends Account> S save(S entity) {
		String password = entity.getPassword();
		if (entity.isNew()) {
			password = passwordEncoder.encode(password);
		} else {
			Optional<Account> old = this.findById(entity.getId());
			if(old.isPresent()) {
				password = old.get().getPassword();
			}
		}
		entity.setCreateTime(new Date());
		entity.setPassword(password);
		return this.accountDao.save(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails account = this.accountDao.findByUsernameIgnoreCase(username);
		return account;
	}

}
