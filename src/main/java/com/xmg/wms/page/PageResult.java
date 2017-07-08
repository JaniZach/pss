package com.xmg.wms.page;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter@ToString
public class PageResult {
	private List<?> listData = new ArrayList<>();
	private Integer totalCount;
	private Integer currentPage;
	private Integer pageSize;
	private Integer totalPage;
	private Integer prevPage;
	private Integer nextPage;

	public PageResult(List<?> listData, Integer totalCount,
			Integer currentPage, Integer pageSize) {
		super();
		this.listData = listData;
		this.totalCount = totalCount;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalPage = this.totalCount % this.pageSize == 0 ? this.totalCount
				/ this.pageSize : this.totalCount / this.pageSize + 1;
		this.prevPage = this.currentPage - 1 > 0 ? this.currentPage - 1 : 1;
		this.nextPage = this.currentPage + 1 > this.totalPage ? this.totalPage
				: this.currentPage + 1;
	}
}
