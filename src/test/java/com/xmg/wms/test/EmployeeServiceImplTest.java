package com.xmg.wms.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xmg.wms.domain.Employee;
import com.xmg.wms.page.PageResult;
import com.xmg.wms.query.EmployeeQueryObject;
import com.xmg.wms.service.IEmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EmployeeServiceImplTest {

	@Autowired
	private IEmployeeService empService;
	@Test
	public void testSave() {
		Employee e = new Employee();
		e.setName("will");
		e.setAge(17);
		e.setAdmin(true);
		e.setPassword("1234");
		e.setEmail("will@qq.com");
		empService.save(e);
	}

	@Test
	public void testDelete() {
		empService.delete(2L);
	}

	@Test
	public void testUpdate() {
		Employee e = new Employee();
		e.setName("neld");
		e.setId(2L);
		empService.update(e);
	}

	@Test
	public void testGet() {
		Employee e = empService.get(2L);
		System.out.println(e);
	}

	@Test
	public void testList() {
		List<Employee> list = empService.list();
		System.out.println(list);
	}
	@Test
	public void testPageQuery() throws Exception {
		EmployeeQueryObject qo = new EmployeeQueryObject();
		qo.setKeyword("w");
		PageResult result = empService.pageQuery(qo);
		System.out.println(result);
	}
}
