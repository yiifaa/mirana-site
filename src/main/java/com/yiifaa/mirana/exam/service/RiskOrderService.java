package com.yiifaa.mirana.exam.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.yiifaa.mirana.exam.domain.RiskOrder;
import com.yiifaa.mirana.exam.query.RiskOrderQuery;
import com.yiifaa.mirana.persistence.GenericService;

public interface RiskOrderService extends GenericService<RiskOrder, Long> {

	Page<RiskOrder> find(RiskOrderQuery pageQuery);

	RiskOrder book(String userId, String username, Long id);
	
	List<RiskOrder> findByUserId(String userId);
	
	List<RiskOrder> findByState(Integer state);
	
	RiskOrder accept(Long id);
	
	RiskOrder complete(Long id);
}
