package com.yiifaa.mirana.exam.service;

import org.springframework.data.domain.Page;

import com.yiifaa.mirana.exam.domain.RiskExam;
import com.yiifaa.mirana.exam.query.RiskExamQuery;
import com.yiifaa.mirana.persistence.GenericService;

public interface RiskExamService extends GenericService<RiskExam, Long> {
	
	Page<RiskExam> find(RiskExamQuery pageQuery);
	
}
