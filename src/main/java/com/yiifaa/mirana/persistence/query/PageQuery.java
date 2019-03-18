package com.yiifaa.mirana.persistence.query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

/**
 * 带分页的查询对象
 * 
 * @author <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version 1.0 开发日期：2017年6月2日 ： 下午3:07:55
 */
public abstract class PageQuery<T> implements Pageable {

	private static final int START_PAGE = 1;

	private static final int PAGE_SIZE = 10;

	private Integer taskId;

	/**
	 * 查询对象
	 */
	private T searchForm;

	/**
	 * 当前页码
	 */
	private int page = START_PAGE;

	/**
	 * 每页数量
	 */
	private int size = PAGE_SIZE;

	/**
	 * 排序方向
	 */
	private Direction dirt = Direction.DESC;

	/**
	 * 排序字段
	 */
	private String sd;

	/**
	 * 初始化默认的查询表单
	 */
	protected abstract T initSearchForm();

	/**
	 * 初始化对象
	 */
	public PageQuery() {
		this.searchForm = initSearchForm();
	}

	/**
	 * @return the searchForm
	 */
	public T getSearchForm() {
		return searchForm;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	/**
	 * @param searchForm
	 *            the searchForm to set
	 */
	public void setSearchForm(T searchForm) {
		this.searchForm = searchForm;
	}

	public int getPageSize() {
		if (this.size < 1) {
			return PAGE_SIZE;
		}
		return size;
	}

	public int getPageNumber() {
		if (this.page < 1) {
			return START_PAGE;
		}
		return page;
	}

	public long getOffset() {
		return (getPageNumber() - START_PAGE) * getPageSize();
	}

	public Sort getSort() {
		return new Sort(this.getDirt(), this.getSd());
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return the dirt
	 */
	public Direction getDirt() {
		if (dirt == null) {
			return Direction.DESC;
		}
		return dirt;
	}

	/**
	 * @param dirt
	 *            the dirt to set
	 */
	public void setDirt(Direction dirt) {
		this.dirt = dirt;
	}

	/**
	 * @return the sd
	 */
	public String getSd() {
		// 在统计时可能出错
		if (StringUtils.isBlank(sd)) {
			return QueryUtil.ID;
		}
		return sd;
	}

	/**
	 * 计算需要获取的第一条记录
	 * 
	 * @return 需要获取的第一条记录
	 */
	public int getFirstResult() {
		return (this.getPageNumber() - 1) * this.getPageSize();
	}

	/**
	 * 计算需要获取的最后一条记录
	 * 
	 * @return 需要获取的最后一条记录
	 */
	public int getMaxResults() {
		return this.getFirstResult() + this.getPageSize();
	}

	/**
	 * @param sd
	 *            the sd to set
	 */
	public void setSd(String sd) {
		this.sd = sd;
	}

	public boolean hasPrevious() {
		return page > START_PAGE;
	}

	public Pageable previousOrFirst() {
		return hasPrevious() ? previous() : first();
	}

	public Pageable next() {
		return PageRequest.of(getPageNumber() + 1, getPageSize(), getSort());
	}

	public Pageable previous() {
		return getPageNumber() == START_PAGE ? this : PageRequest.of(getPageNumber() - 1, getPageSize(), getSort());
	}

	public Pageable first() {
		return PageRequest.of(START_PAGE, getPageSize(), getSort());
	}

}
