package com.xmg.wms.query;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class QueryObject {
	private Integer currentPage = 1;
	private Integer pageSize = 5;
	public Integer getBeginIndex(){
		return (this.currentPage-1)*this.pageSize;
	}
}
