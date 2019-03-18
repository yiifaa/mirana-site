package com.yiifaa.mirana.commons;

import static org.junit.Assert.*;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;

import com.yiifaa.mirana.BaseTest;
import com.yiifaa.mirana.commons.domain.Role;
import com.yiifaa.mirana.commons.service.RoleService;

public class RoleServiceTest extends BaseTest {
	
	@Resource
	private RoleService roleService;

	@Test
	public void testPersist() {
		Role base = new Role();
		base.setName("BASE");
		base.setCreateTime(new Date());
		base.setRemarks("基本权限");
		this.roleService.save(base);
	}

}
