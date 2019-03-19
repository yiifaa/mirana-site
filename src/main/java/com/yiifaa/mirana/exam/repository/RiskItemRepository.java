package com.yiifaa.mirana.exam.repository;

import org.springframework.stereotype.Repository;

import com.yiifaa.mirana.exam.domain.RiskItem;
import com.yiifaa.mirana.persistence.GenericRepository;

@Repository("riskItemRepository")
public interface RiskItemRepository extends GenericRepository<RiskItem, Long> {

}
