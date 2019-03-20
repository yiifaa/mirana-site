package com.yiifaa.mirana.exam.service;

import java.util.List;

import com.yiifaa.mirana.exam.domain.ExamState;
import com.yiifaa.mirana.exam.helper.ExamStateHelper;
import com.yiifaa.mirana.persistence.GenericService;

public interface ExamStateService extends GenericService<ExamState, Long>  {
	
//	List<Map<String, Object>> findByUserId(String userId);
	
	List<ExamStateHelper> findByUserId(String userId);
	
	ExamState start(String userId, Long exId);
	
	ExamState complete(Long sid);
	
}
