package com.yiifaa.mirana.commons;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.hamcrest.core.IsNull;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.yiifaa.mirana.BaseTest;
import com.yiifaa.mirana.commons.domain.Account;
import com.yiifaa.mirana.commons.service.AccountService;

public class AccountServiceTest extends BaseTest {
	
	@Resource
	private AccountService accountService;

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

}
