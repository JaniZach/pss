package com.xmg.wms.service;

import java.util.List;

import com.xmg.wms.domain.Employee;
import com.xmg.wms.page.PageResult;
import com.xmg.wms.query.QueryObject;

public interface IEmployeeService {
	void save(Employee dept);
	void delete(Long id);
	void update(Employee dept);
	Employee get(Long id);
	List<Employee> list();
	//高级查询
	PageResult pageQuery(QueryObject qo);
	Employee checkLogin(String username, String password);

    void batchDelete(List<Long> batchList);
}
