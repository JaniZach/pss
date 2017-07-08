package com.xmg.wms.service;

import java.util.List;

import com.xmg.wms.domain.Permission;
import com.xmg.wms.page.PageResult;
import com.xmg.wms.query.QueryObject;

public interface IPermissionService {
	void save(Permission p);
	void delete(Long id);
	List<Permission> list();
	//高级查询
	PageResult pageQuery(QueryObject qo);
	//加载权限
	void reload();
	//批量删除
	void batchDelete(List<Long> batchList);
}
