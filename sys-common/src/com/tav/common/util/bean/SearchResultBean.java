package com.tav.common.util.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Đối tượng chứa kết quả tìm kiếm
 * 
 * @author pmdn_tutm3
 * @Time: Jan 18, 2013
 * @param <T>
 */
public class SearchResultBean<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long resultCount = 0L;
	private List<T> resultList;
	private Integer startPosition = 0;

	public SearchResultBean() {
	}

	public Long getResultCount() {
		return resultCount;
	}

	public void setResultCount(Long resultCount) {
		this.resultCount = resultCount;
	}

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

	public void setStartPosition(Integer startPosition) {
		this.startPosition = startPosition;
	}

	public Integer getStartPosition() {
		return startPosition;
	}
}
