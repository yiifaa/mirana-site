package com.yiifaa.mirana.commons;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.hamcrest.core.IsNull;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.yiifaa.mirana.BaseTest;
import com.yiifaa.mirana.commons.domain.Account;
import com.yiifaa.mirana.commons.domain.Role;
import com.yiifaa.mirana.commons.service.AccountService;
import com.yiifaa.mirana.commons.service.RoleService;

public class AccountServiceTest extends BaseTest {
	
	@Resource
	private AccountService accountService;
	
	@Resource
	private RoleService roleService;

	@Test
	@Ignore
	@Rollback(false)
	public void testPersist() {
		assertThat(accountService, IsNull.notNullValue());
		Account account = new Account();
		account.setUsername("admin");
		account.setPassword("admin123");
		account.setConfirmPassword("admin123");
		accountService.save(account);
	}
	
	@Ignore
	@Test
	public void testClient() {
		assertThat(accountService, IsNull.notNullValue());
		Account account = new Account();
		account.setUsername("client");
		account.setPassword("client");
		account.setConfirmPassword("client");
		accountService.persistByType(account, AccountType.CLIENT);
	}
	
	@Ignore
	@Test
	public void testSale() {
		assertThat(accountService, IsNull.notNullValue());
		Account account = new Account();
		account.setUsername("sale");
		account.setPassword("sale");
		account.setConfirmPassword("sale");
		accountService.persistByType(account, AccountType.SALE);
	}
	
	@Test
	public void testAdmin() {
		assertThat(accountService, IsNull.notNullValue());
		Account account = accountService.findByUsername("client");
		Role admin = this.roleService.findByName(AccountType.CLIENT);
		account.addRole(admin);
		accountService.save(account);
	}

}
