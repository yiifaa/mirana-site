package com.yiifaa.mirana.persistence;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.yiifaa.mirana.persistence.query.PageQuery;
import com.yiifaa.mirana.persistence.query.PageQueryBuilder;


/**
 * 
 * 
 * @author <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version 1.0 开发日期：2017年6月2日 ： 上午11:01:00
 */
@NoRepositoryBean
public interface GenericRepository<E extends Identifiable<ID>, ID extends Serializable>
		extends PagingAndSortingRepository<E, ID> {

	/**
	 * 根据分页对象进行分页查询,仅支持HQL\JQL查询
	 * 
	 * @param queryPage
	 *            分页对象
	 * @param builder
	 *            分页查询辅助对象
	 * @return 业务实体的分页结果
	 */
	<S> Page<E> find(PageQuery<S> queryPage, PageQueryBuilder<S> builder);

}
