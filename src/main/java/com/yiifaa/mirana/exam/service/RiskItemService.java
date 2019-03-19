package com.yiifaa.mirana.exam.service;

import org.springframework.data.domain.Page;

import com.yiifaa.mirana.exam.domain.RiskItem;
import com.yiifaa.mirana.exam.query.RiskItemQuery;
import com.yiifaa.mirana.persistence.GenericService;

public interface RiskItemService extends GenericService<RiskItem, Long> {
	
	Page<RiskItem> find(RiskItemQuery pageQuery);
	
}
