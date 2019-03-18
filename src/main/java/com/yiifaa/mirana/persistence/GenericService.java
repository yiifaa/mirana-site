package com.yiifaa.mirana.persistence;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;

import com.yiifaa.mirana.persistence.query.PageQuery;
import com.yiifaa.mirana.persistence.query.PageQueryBuilder;

/**
 * 
 * 
 * @author <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version 1.0 开发日期：2017年6月2日 ： 下午12:02:45
 */
@NoRepositoryBean
public interface GenericService<E extends Identifiable<ID>, ID extends Serializable> extends GenericRepository<E, ID> {

	/**
	 * 获取持久化实例
	 * 
	 * @return
	 */
	GenericRepository<E, ID> getRepository();

	/**
	 * 统计数量
	 * 
	 * @return 获取统计数量
	 */
	@Override
	default long count() {
		return this.getRepository().count();
	}

	/**
	 * 根据ID删除持久化对象
	 * 
	 * @param id:
	 *            持久化对象的ID
	 */
	@Override
	default void deleteById(ID id) {
		this.getRepository().deleteById(id);
	}

	/**
	 * 删除持久化对象
	 * 
	 * @param entity:
	 *            持久化对象
	 */
	@Override
	default void delete(E entity) {
		this.getRepository().delete(entity);
	}

	/**
	 * 批量删除持久化对象
	 * 
	 * @param entities:
	 *            持久化对象
	 */
	default void deleteAll(Iterable<? extends E> entities) {
		this.getRepository().deleteAll(entities);
	}

	/**
	 * 删除所有对象
	 */
	default void deleteAll() {
		this.getRepository().deleteAll();
	}

	/**
	 * 判断是否存在持久化对象
	 * 
	 * @param id:
	 *            持久化对象主键
	 * @return 是否存在的结果
	 */
	default boolean existsById(ID id) {
		return this.getRepository().existsById(id);
	}

	/**
	 * 根据SQL、HQL语句进行分页查询
	 * 
	 */
	default <S> Page<E> find(PageQuery<S> pageQuery, PageQueryBuilder<S> builder) {
		return this.getRepository().find(pageQuery, builder);
	}

	/**
	 * 根据id查找持久化对象
	 * 
	 * @param id:
	 *            持久化对象主键
	 */
	default Optional<E> findById(ID id) {
		return this.getRepository().findById(id);
	}

	/**
	 * 获取所有的持久化对象
	 * 
	 * @return 所有的持久化对象
	 */
	default Iterable<E> findAll() {
		return this.getRepository().findAll();
	}

	/**
	 * 根据ID集合获取相关的持久化对象
	 * 
	 * @return id包含在ids之内的集合对象
	 */
	default Iterable<E> findAllById(Iterable<ID> ids) {
		return this.getRepository().findAllById(ids);
	}

	/**
	 * 根据排序方向获取所有的集合对象
	 * 
	 * @return 排序后的集合对象
	 */
	default Iterable<E> findAll(Sort sort) {
		return this.getRepository().findAll(sort);
	}

	/**
	 * 分页查询
	 * 
	 * @return 获取分页的对象
	 */
	default Page<E> findAll(Pageable pageable) {
		return this.getRepository().findAll(pageable);
	}

	/**
	 * 保存实例，含持久化与合并
	 * 
	 * @return 持久化后的对象
	 */
	default <S extends E> S save(S entity) {
		return this.getRepository().save(entity);
	}

	/**
	 * 批量保存实例
	 * 
	 * @return 持久化后的对象
	 */
	default <S extends E> Iterable<S> saveAll(Iterable<S> entities) {
		return this.getRepository().saveAll(entities);
	}

}
