package com.yiifaa.mirana.exam.query;

import com.yiifaa.mirana.exam.domain.RiskItem;
import com.yiifaa.mirana.persistence.query.PageQuery;

public class RiskItemQuery extends PageQuery<RiskItem> {

	@Override
	protected RiskItem initSearchForm() {
		return new RiskItem();
	}

}
