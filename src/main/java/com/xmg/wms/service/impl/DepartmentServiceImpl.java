package com.xmg.wms.service.impl;

import java.util.Collections;
import java.util.List;

import com.xmg.wms.domain.Department;
import com.xmg.wms.mapper.DepartmentMapper;
import com.xmg.wms.mapper.EmployeeMapper;
import com.xmg.wms.page.PageResult;
import com.xmg.wms.query.QueryObject;
import com.xmg.wms.service.IDepartmentService;

import lombok.Setter;

@Setter
public class DepartmentServiceImpl implements IDepartmentService {
	private DepartmentMapper deptMapper;
	private EmployeeMapper empMapper;

	@Override
	public void save(Department dept) {
		deptMapper.save(dept);
	}

	@Override
	public void delete(Long id) {
		// 删除部门时,需要将本部门内所有员工的dept_id设置为null,再删除部门
		empMapper.updateByRelation(id);
		deptMapper.delete(id);
	}

	@Override
	public void update(Department dept) {
		deptMapper.update(dept);
	}

	@Override
	public Department get(Long id) {
		return deptMapper.get(id);
	}

	@Override
	public List<Department> list() {
		return deptMapper.list();
	}

	@Override
	public PageResult pageQuery(QueryObject qo) {
		Long totalCount = deptMapper.queryCount(qo);
		if (totalCount == 0) {
			return new PageResult(Collections.EMPTY_LIST, 0,
					1, qo.getPageSize());
		}
		List<Department> listData = deptMapper.queryList(qo);
		return new PageResult(listData, totalCount.intValue(),
				qo.getCurrentPage(), qo.getPageSize());
	}

	@Override
	public void batchDelete(List<Long> batchList) {
		// 删除部门时,需要将本部门内所有员工的dept_id设置为null,再删除部门
		for (Long deleteId : batchList) {
			empMapper.updateByRelation(deleteId);
		}
		//进行批量删除操作
		deptMapper.batchDelete(batchList);
	}
}
