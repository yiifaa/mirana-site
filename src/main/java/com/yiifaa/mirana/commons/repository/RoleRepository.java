package com.yiifaa.mirana.commons.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yiifaa.mirana.commons.domain.Role;
import com.yiifaa.mirana.persistence.GenericRepository;

/**
 * 角色存储接口
 * 
 * @author <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version 1.0 开发日期：2017年6月7日 ： 上午10:21:58
 */
@Repository
public interface RoleRepository extends GenericRepository<Role, String> {
	
	List<Role> findByName(String name);
	
}
