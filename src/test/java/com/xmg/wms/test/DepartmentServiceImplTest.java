package com.xmg.wms.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xmg.wms.domain.Department;
import com.xmg.wms.service.IDepartmentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DepartmentServiceImplTest {

	@Autowired
	private IDepartmentService deptService;
	@Test
	public void testSave() {
		Department dept = new Department();
		dept.setName("营销部");
		dept.setSn("yx-001");
		deptService.save(dept);
	}

	@Test
	public void testDelete() {
		deptService.delete(7L);
	}

	@Test
	public void testUpdate() {
		Department dept = new Department();
		dept.setId(6L);
		dept.setName("市场部");
		dept.setSn("sc-002");
		deptService.update(dept);
	}

	@Test
	public void testGet() {
		Department dept = deptService.get(2L);
		System.out.println(dept);
	}

	@Test
	public void testList() {
		List<Department> list = deptService.list();
		System.out.println(list);
	}
}
