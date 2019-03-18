package com.yiifaa.mirana.persistence.query;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * 
 * @author <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version 1.0 开发日期：2017年6月4日 ： 下午9:27:18
 */
public interface QueryUtil {

	final static String LIKE = "%";

	final static String ID = "id";

	/**
	 * 左模糊查询
	 * 
	 * @param value
	 * @return
	 */
	public static String buildLeftLike(String value) {
		return LIKE + value;
	}

	/**
	 * 右模糊查询
	 * 
	 * @param value
	 * @return
	 */
	public static String buildRightLike(String value) {
		return LIKE + value;
	}

	/**
	 * 全模糊匹配
	 * 
	 * @param value
	 * @return
	 */
	public static String buildLike(String value) {
		return LIKE + value + LIKE;
	}

	/**
	 * 
	 * @param sd
	 * @param dir
	 * @return
	 */
	public static String buildOrderBy(String alias, String column, String order) {
		if (StringUtils.isBlank(column)) {
			column = ID;
		}
		return " order by " + alias + "." + column + " " + order;
	}
}
