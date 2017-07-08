package com.xmg.wms.mapper;

import java.util.List;

import com.xmg.wms.query.QueryObject;

public interface BaseMapper<T> {
	void save(T t);
	void delete(Long id);
	void update(T t);
	T get(Long id);
	List<T> list();
	//高级查询,分页部分
	List<T> queryList(QueryObject qo);
	Long queryCount(QueryObject qo);

	//批量删除
	void batchDelete(List<Long> batchList);
}
