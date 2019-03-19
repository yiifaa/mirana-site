package com.yiifaa.mirana.exam.service.impl;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableMap.Builder;
import com.yiifaa.mirana.exam.domain.RiskExam;
import com.yiifaa.mirana.exam.query.RiskExamQuery;
import com.yiifaa.mirana.exam.repository.RiskExamRepository;
import com.yiifaa.mirana.exam.service.RiskExamService;
import com.yiifaa.mirana.persistence.GenericRepository;
import com.yiifaa.mirana.persistence.query.PageQueryBuilder;
import com.yiifaa.mirana.persistence.query.QueryUtil;

@Service
public class RiskExamServiceImpl implements RiskExamService {
	
	private RiskExamRepository riskExamDao;
	
	@Inject
	public RiskExamServiceImpl(RiskExamRepository riskExamDao) {
		super();
		this.riskExamDao = riskExamDao;
	}

	@Override
	public GenericRepository<RiskExam, Long> getRepository() {
		return riskExamDao;
	}

	@Override
	public Page<RiskExam> find(RiskExamQuery pageQuery) {
		return this.find(pageQuery, new PageQueryBuilder<RiskExam>() {

			@Override
			public String buildSelect() {
				return "select exam from RiskExam exam ";
			}

			@Override
			public String buildWhere(RiskExam s, Builder<String, Object> params) {
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
				return "select count(exam) from RiskExam exam ";
			}
			
			
		});
	}

}
