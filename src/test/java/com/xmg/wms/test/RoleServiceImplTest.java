package com.xmg.wms.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xmg.wms.domain.Role;
import com.xmg.wms.service.IRoleService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class RoleServiceImplTest {
	@Autowired
	private IRoleService roleService;

	@Test
	public void testSave() {
		Role p = new Role();
		p.setName("员工管理员");
		p.setSn("EMP_MGR");
		roleService.save(p );
	}

	@Test
	public void testDelete() {
		roleService.delete(2L);
	}

	@Test
	public void testList() {
		List<Role> list = roleService.list();
		System.out.println(list);
	}
}
