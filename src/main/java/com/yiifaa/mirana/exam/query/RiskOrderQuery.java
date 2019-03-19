package com.yiifaa.mirana.exam.query;

import com.yiifaa.mirana.exam.domain.RiskOrder;
import com.yiifaa.mirana.persistence.query.PageQuery;

public class RiskOrderQuery extends PageQuery<RiskOrder> {

	@Override
	protected RiskOrder initSearchForm() {
		return new RiskOrder();
	}

}
