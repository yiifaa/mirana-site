package com.yiifaa.mirana.exam.service;

import org.springframework.data.domain.Page;

import com.yiifaa.mirana.exam.domain.RiskOrder;
import com.yiifaa.mirana.exam.query.RiskOrderQuery;
import com.yiifaa.mirana.persistence.GenericService;

public interface RiskOrderService extends GenericService<RiskOrder, Long> {

	Page<RiskOrder> find(RiskOrderQuery pageQuery);
	
}
