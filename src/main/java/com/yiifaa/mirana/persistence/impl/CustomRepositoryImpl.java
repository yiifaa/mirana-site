package com.yiifaa.mirana.persistence.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableMap;
import com.yiifaa.mirana.persistence.GenericRepository;
import com.yiifaa.mirana.persistence.Identifiable;
import com.yiifaa.mirana.persistence.query.PageQuery;
import com.yiifaa.mirana.persistence.query.PageQueryBuilder;

/**
 * 
 * 
 * @author <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version 1.0 开发日期：2017年6月2日 ： 上午11:05:00
 */
public class CustomRepositoryImpl<E extends Identifiable<ID>, ID extends Serializable>
		extends SimpleJpaRepository<E, ID> implements GenericRepository<E, ID> {

	private final Logger log = LoggerFactory.getLogger(CustomRepositoryImpl.class);

	private final EntityManager entityManager;

	private final Class<E> domainClass;

	/**
	 * @param entityInformation
	 * @param entityManager
	 */
	public CustomRepositoryImpl(JpaEntityInformation<E, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
		this.domainClass = entityInformation.getJavaType();
	}

	/**
	 * @param domainClass
	 * @param em
	 */
	public CustomRepositoryImpl(Class<E> domainClass, EntityManager em) {
		super(domainClass, em);
		this.entityManager = em;
		this.domainClass = domainClass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cnitsec.mirana.persistence.GenericRepository#find(com.cnitsec.mirana.
	 * query.PageQuery, com.cnitsec.mirana.query.PageQueryBuilder)
	 */
	@Override
	@Transactional(readOnly = true)
	public <S> Page<E> find(PageQuery<S> queryPage, PageQueryBuilder<S> builder) {
		final StringBuilder countSql = new StringBuilder();
		final StringBuilder domainSql = new StringBuilder();
		final ImmutableMap.Builder<String, Object> params = ImmutableMap.builder();
		// 依次组合SQL
		String select = builder.buildSelect();
		String count = builder.buildCount();
		String where = builder.buildWhere(queryPage.getSearchForm(), params);
		String bys = builder.buildBys(queryPage.getSd(), queryPage.getDirt().name());
		ImmutableMap<String, Object> parameters = params.build();
		//
		countSql.append(count).append(" where 1=1 ").append(where).append(bys);
		if (log.isDebugEnabled()) {
			log.debug("分页查询count语句为：{}", countSql.toString());
		}
		TypedQuery<Long> countQuery = this.entityManager.createQuery(countSql.toString(), Long.class);
		for (Entry<String, ?> paramEntry : parameters.entrySet()) {
			countQuery.setParameter(paramEntry.getKey(), paramEntry.getValue());
		}
		Long total = countQuery.getSingleResult();
		//
		domainSql.append(select).append(" where 1=1 ").append(where).append(bys);
		if (log.isDebugEnabled()) {
			log.debug("分页查询实体语句为：{}", domainSql.toString());
		}
		TypedQuery<E> domainQuery = this.entityManager.createQuery(domainSql.toString(), this.domainClass);
		domainQuery.setFirstResult(queryPage.getFirstResult());
		domainQuery.setMaxResults(queryPage.getMaxResults());
		for (Entry<String, ?> paramEntry : parameters.entrySet()) {
			domainQuery.setParameter(paramEntry.getKey(), paramEntry.getValue());
		}
		List<E> domainResult = domainQuery.getResultList();
		//
		return new PageImpl<E>(domainResult, queryPage, total);
	}

}
