package com.yiifaa.mirana.persistence.query;

/**
 * @since 1.0 2014年8月29日,下午8:56:23 分页查询构造器，依次构造count查询语句、list查询语句以及where查询条件
 * @author <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version 1.0
 */
public interface PageQueryBuilder<S> extends QueryBuilder<S> {

	/**
	 * 构建count子句
	 * 
	 * @return count子句
	 */
	String buildCount();
}
