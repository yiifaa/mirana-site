package com.yiifaa.mirana.persistence;

import org.apache.commons.lang3.StringUtils;

/**
 * 持久对象的主键接口
 * 
 * @author <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version 1.0 开发日期：2017年6月2日 ： 上午10:42:21
 */
public interface Identifiable<ID> {

	/**
	 * 获取实体类的主键
	 * 
	 * @return 主键
	 */
	ID getId();

	/**
	 * 判断主键是否已设置
	 * 
	 * @return 主键是否已设置
	 */
	default boolean hasId() {
		ID id = this.getId();
		if (id instanceof String) {
			return StringUtils.isNotBlank((String) id);
		}
		return id != null;
	}

	/**
	 * 根据ID判断对象是否是新建状态(未持久化) 能提高Spring JPA的持久化速度
	 * 
	 * @return 是否是新建状态
	 */
	default boolean isNew() {
		return !this.hasId();
	}

	/**
	 * 设置主键
	 * 
	 * @param id
	 *            主键
	 */
	void setId(ID id);

	/**
	 * 
	 * @return
	 */
	abstract int hashCode();

	boolean equals(Object obj);

	String toString();
}
