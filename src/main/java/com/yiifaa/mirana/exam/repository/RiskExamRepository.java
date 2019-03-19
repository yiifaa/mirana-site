package com.yiifaa.mirana.exam.repository;

import org.springframework.stereotype.Repository;

import com.yiifaa.mirana.exam.domain.RiskExam;
import com.yiifaa.mirana.persistence.GenericRepository;

@Repository("riskExamRepository")
public interface RiskExamRepository extends GenericRepository<RiskExam, Long> {

}
