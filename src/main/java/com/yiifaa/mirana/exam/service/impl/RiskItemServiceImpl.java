package com.yiifaa.mirana.exam.service.impl;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableMap.Builder;
import com.yiifaa.mirana.exam.domain.RiskItem;
import com.yiifaa.mirana.exam.query.RiskItemQuery;
import com.yiifaa.mirana.exam.repository.RiskItemRepository;
import com.yiifaa.mirana.exam.service.RiskItemService;
import com.yiifaa.mirana.persistence.GenericRepository;
import com.yiifaa.mirana.persistence.query.PageQueryBuilder;
import com.yiifaa.mirana.persistence.query.QueryUtil;

@Service
public class RiskItemServiceImpl implements RiskItemService {
	
	private RiskItemRepository RiskItemDao;
	
	@Inject
	public RiskItemServiceImpl(RiskItemRepository RiskItemDao) {
		super();
		this.RiskItemDao = RiskItemDao;
	}

	@Override
	public GenericRepository<RiskItem, Long> getRepository() {
		return RiskItemDao;
	}

	@Override
	public Page<RiskItem> find(RiskItemQuery pageQuery) {
		return this.find(pageQuery, new PageQueryBuilder<RiskItem>() {

			@Override
			public String buildSelect() {
				return "select exam from RiskItem exam ";
			}

			@Override
			public String buildWhere(RiskItem s, Builder<String, Object> params) {
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
				return "select count(exam) from RiskItem exam ";
			}
			
			
		});
	}

}
