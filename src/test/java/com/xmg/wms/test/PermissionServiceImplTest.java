package com.xmg.wms.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xmg.wms.domain.Permission;
import com.xmg.wms.service.IPermissionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class PermissionServiceImplTest {
	@Autowired
	private IPermissionService permissionService;

	@Test
	public void testSave() {
		Permission p = new Permission();
		p.setName("员工列表");
		p.setExpression("com.xmg.pss.web.action.EmployeeAction:list");
		permissionService.save(p);
	}

	@Test
	public void testDelete() {
	}

	@Test
	public void testList() {
	}

	@Test
	public void testPageQuery() {
	}

	@Test
	public void testSetPermissionMapper() {
	}

}
