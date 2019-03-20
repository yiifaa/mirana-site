package com.yiifaa.mirana.exam.repository;

import org.springframework.stereotype.Repository;

import com.yiifaa.mirana.exam.domain.ExamState;
import com.yiifaa.mirana.persistence.GenericRepository;

@Repository("examStateRepository")
public interface ExamStateRepository extends GenericRepository<ExamState, Long> {

}
