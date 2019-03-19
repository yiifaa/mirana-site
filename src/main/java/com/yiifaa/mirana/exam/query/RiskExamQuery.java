package com.yiifaa.mirana.exam.query;

import com.yiifaa.mirana.exam.domain.RiskExam;
import com.yiifaa.mirana.persistence.query.PageQuery;

public class RiskExamQuery extends PageQuery<RiskExam> {

	@Override
	protected RiskExam initSearchForm() {
		return new RiskExam();
	}

}
