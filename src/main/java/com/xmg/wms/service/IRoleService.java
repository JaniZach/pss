package com.xmg.wms.service;

import java.util.List;

import com.xmg.wms.domain.Role;
import com.xmg.wms.page.PageResult;
import com.xmg.wms.query.QueryObject;

public interface IRoleService {
	void save(Role r);
	void delete(Long id);
	void update(Role r);
	Role get(Long id);
	List<Role> list();
	PageResult pageQuery(QueryObject qo);
	void batchDelete(List<Long> ids);
}
