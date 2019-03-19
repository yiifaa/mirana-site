package com.yiifaa.mirana.commons;

import static org.junit.Assert.*;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;

import com.yiifaa.mirana.BaseTest;
import com.yiifaa.mirana.commons.domain.Role;
import com.yiifaa.mirana.commons.service.RoleService;

public class RoleServiceTest extends BaseTest {
	
	@Resource
	private RoleService roleService;

	@Ignore
	@Test
	public void testPersist() {
		Role base = new Role();
		base.setName("BASE");
		base.setCreateTime(new Date());
		base.setRemarks("基本权限");
		this.roleService.save(base);
	}
	
	@Test
	public void testSALE() {
		Role base = new Role();
		base.setName("SALE");
		base.setCreateTime(new Date());
		base.setRemarks("销售权限");
		this.roleService.save(base);
	}
	
	@Test
	public void testClient() {
		Role base = new Role();
		base.setName("CLIENT");
		base.setCreateTime(new Date());
		base.setRemarks("客户权限");
		this.roleService.save(base);
	}
	
	@Test
	public void testADMIN() {
		Role base = new Role();
		base.setName("ADMIN");
		base.setCreateTime(new Date());
		base.setRemarks("管理员权限");
		this.roleService.save(base);
	}

}
