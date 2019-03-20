package com.yiifaa.mirana.exam.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableMap.Builder;
import com.yiifaa.mirana.commons.domain.Account;
import com.yiifaa.mirana.commons.utils.SecurityContextUtils;
import com.yiifaa.mirana.exam.domain.RiskItem;
import com.yiifaa.mirana.exam.domain.RiskOrder;
import com.yiifaa.mirana.exam.query.RiskOrderQuery;
import com.yiifaa.mirana.exam.repository.RiskItemRepository;
import com.yiifaa.mirana.exam.repository.RiskOrderRepository;
import com.yiifaa.mirana.exam.service.RiskOrderService;
import com.yiifaa.mirana.persistence.GenericRepository;
import com.yiifaa.mirana.persistence.query.PageQueryBuilder;
import com.yiifaa.mirana.persistence.query.QueryUtil;

@Service
public class RiskOrderServiceImpl implements RiskOrderService {
	
	private RiskOrderRepository riskOrderDao;
	
	private RiskItemRepository riskItemDao;
	
	@Inject
	public RiskOrderServiceImpl(RiskOrderRepository riskOrderDao, RiskItemRepository riskItemDao) {
		super();
		this.riskOrderDao = riskOrderDao;
		this.riskItemDao = riskItemDao;
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

	@Override
	public RiskOrder book(String userId, String username, Long id) {
		Optional<RiskItem> item = this.riskItemDao.findById(id);
		RiskOrder order = new RiskOrder();
		order.setCreateTime(new Date());
		order.setRiskId(id);
		if(item.isPresent()) {
			order.setName(item.get().getName());
		}
		order.setState(0);
		order.setUserId(userId);
		order.setClientName(username);
		return this.riskOrderDao.save(order);
	}

	@Override
	public List<RiskOrder> findByUserId(String userId) {
		return this.riskOrderDao.findByUserId(userId);
	}

	@Override
	public List<RiskOrder> findByState(Integer state) {
		return this.riskOrderDao.findByState(state);
	}

	@Override
	public RiskOrder accept(Long id) {
		Optional<RiskOrder> order = this.riskOrderDao.findById(id);
		if(order.isPresent()) {
			RiskOrder ro = order.get();
			ro.setState(10);
			Account account = SecurityContextUtils.current();
			if(account != null) {
				ro.setSaleName(account.getUsername());
			}
			this.riskOrderDao.save(ro);
		}
		return null;
	}

	@Override
	public RiskOrder complete(Long id) {
		Optional<RiskOrder> order = this.riskOrderDao.findById(id);
		if(order.isPresent()) {
			RiskOrder ro = order.get();
			ro.setState(100);
			Account account = SecurityContextUtils.current();
			if(account != null) {
				ro.setSaleName(account.getUsername());
			}
			this.riskOrderDao.save(ro);
		}
		return null;
	}

}
