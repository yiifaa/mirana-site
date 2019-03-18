package com.yiifaa.mirana.persistence.query;

import javax.persistence.TypedQuery;

import com.google.common.collect.ImmutableMap;

/**
 * @since 1.0 2014年8月29日,下午3:40:50 查询构造器
 * @author <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version 1.0
 */
public interface QueryBuilder<S> {

	/**
	 * 构建select子句
	 * 
	 * @return select子句
	 */
	String buildSelect();

	/**
	 * 构建where子句
	 * 
	 * @param s
	 *            查询对象
	 * @param parameters
	 * @return where子句
	 */
	String buildWhere(S s, final ImmutableMap.Builder<String, Object> params);

	/**
	 * 构建groupby与order子句
	 * 
	 * @param column
	 *            排序列
	 * @param order
	 *            排序方向
	 * @return groupby与order子句
	 */
	String buildBys(String column, String order);

	/**
	 * 添加Query Hint
	 * 
	 * @param query
	 */
	default <E> void buildHint(TypedQuery<E> query) {
		// 默认为空
	}

}
