package com.xmg.wms.mapper;


import org.apache.ibatis.annotations.Param;

import com.xmg.wms.domain.Employee;

public interface EmployeeMapper extends BaseMapper<Employee> {
	//当部门删除时,员工的dept_id设置为null
	void updateByRelation(Long deptId);

	void saveRelation(@Param("eid") Long id,@Param("rid") Long id2);

	void deleteRelation(Long id);
	
	Employee checkLogin(@Param("name") String username,@Param("password") String password);

}
