package com.yiifaa.mirana.exam.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yiifaa.mirana.exam.domain.RiskOrder;
import com.yiifaa.mirana.persistence.GenericRepository;

@Repository("riskOrderRepository")
public interface RiskOrderRepository extends GenericRepository<RiskOrder, Long> {
	
	List<RiskOrder> findByUserId(String userId);
	
	List<RiskOrder> findByState(Integer state);
	
}
