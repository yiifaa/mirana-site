package com.yiifaa.mirana.exam.service.impl;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableMap.Builder;
import com.yiifaa.mirana.exam.domain.RiskOrder;
import com.yiifaa.mirana.exam.query.RiskOrderQuery;
import com.yiifaa.mirana.exam.repository.RiskOrderRepository;
import com.yiifaa.mirana.exam.service.RiskOrderService;
import com.yiifaa.mirana.persistence.GenericRepository;
import com.yiifaa.mirana.persistence.query.PageQueryBuilder;
import com.yiifaa.mirana.persistence.query.QueryUtil;

@Service
public class RiskOrderServiceImpl implements RiskOrderService {
	
	private RiskOrderRepository riskOrderDao;
	
	@Inject
	public RiskOrderServiceImpl(RiskOrderRepository riskOrderDao) {
		super();
		this.riskOrderDao = riskOrderDao;
	}

	@Override
	public GenericRepository<RiskOrder, Long> getRepository() {
		return riskOrderDao;
	}

	@Override
	public Page<RiskOrder> find(RiskOrderQuery pageQuery) {
		return this.find(pageQuery, new PageQueryBuilder<RiskOrder>() {

			@Override
			public String buildSelect() {
				return "select exam from RiskOrder exam ";
			}

			@Override
			public String buildWhere(RiskOrder s, Builder<String, Object> params) {
				StringBuilder sql = new StringBuilder();
				String username = s.getName();
				if (StringUtils.isNotBlank(username)) {
					sql.append(" and exam.name like :username");
					params.put("username", QueryUtil.buildLike(username));
				}
				return sql.toString();
			}

			@Override
			public String buildBys(String column, String order) {
				return QueryUtil.buildOrderBy("exam", column, order);
			}

			@Override
			public String buildCount() {
				return "select count(exam) from RiskOrder exam ";
			}
			
			
		});
	}

}
