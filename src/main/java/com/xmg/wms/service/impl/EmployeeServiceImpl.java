package com.xmg.wms.service.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.xmg.wms.util.UserContext;
import lombok.Setter;

import com.opensymphony.xwork2.ActionContext;
import com.xmg.wms.domain.Employee;
import com.xmg.wms.domain.Permission;
import com.xmg.wms.domain.Role;
import com.xmg.wms.mapper.EmployeeMapper;
import com.xmg.wms.page.PageResult;
import com.xmg.wms.query.QueryObject;
import com.xmg.wms.service.IEmployeeService;

public class EmployeeServiceImpl implements IEmployeeService {
	@Setter
	private EmployeeMapper empMapper;

	@Override
	public void save(Employee e) {
		//保存员工的同时,必须保存员工的角色信息
		empMapper.save(e);
		List<Role> roles = e.getRoles();
		for (Role role : roles) {
			empMapper.saveRelation(e.getId(),role.getId());
		}
	}

	@Override
	public void delete(Long id) {
		//删除员工的同时,必须先删除员工的角色信息
		empMapper.deleteRelation(id);
		empMapper.delete(id);
	}

	@Override
	public void update(Employee e) {
		//更新员工的时候,应当先删除员工原来的角色信息,再更新关系
		empMapper.deleteRelation(e.getId());
		List<Role> roles = e.getRoles();
		for (Role role : roles) {
			empMapper.saveRelation(e.getId(),role.getId());
		}
		empMapper.update(e);
	}

	@Override
	public Employee get(Long id) {
		return empMapper.get(id);
	}

	@Override
	public List<Employee> list() {
		return empMapper.list();
	}

	@Override
	public PageResult pageQuery(QueryObject qo) {
		Long totalCount = empMapper.queryCount(qo);
		if (totalCount == 0) {
			//当总记录数为0的时候,此时让当前页为1
			return new PageResult(Collections.EMPTY_LIST, 0,
					1, qo.getPageSize());
		}
		List<Employee> listData = empMapper.queryList(qo);
		return new PageResult(listData, totalCount.intValue(),
				qo.getCurrentPage(), qo.getPageSize());
	}

	@Override
	public Employee checkLogin(String username, String password) {
		//登陆校验的功能
		Employee emp = empMapper.checkLogin(username,password);
		//如果emp为null,应返回登陆界面
		if(emp == null){
			throw new RuntimeException("账号或密码错误,请重新输入!");
		}
		//当账号密码正确时,将用户,权限信息保存到Session中共享
		UserContext.setCurrentUser(emp);
		List<Role> roles = emp.getRoles();
		//将所有权限信息保存到Set集合中(去重)
		Set<String> expressionSet = new HashSet<>();
		for (Role role : roles) {
			List<Permission> permissions = role.getPermissions();
			for (Permission permission : permissions) {
				//至此,获取用户中全部的expression,即获取到全部权限信息
				String expression = permission.getExpression();
				expressionSet.add(expression);
			}
		}
		//将所有权限信息共享到Session中
		UserContext.setCurrentPermissionSet(expressionSet);
		//将用户名分享到session中
		UserContext.setUserName(username);
		return emp;
	}

	@Override
	public void batchDelete(List<Long> batchList) {
		//先删除员工和角色的关系
		for (Long deleteId : batchList) {
			empMapper.deleteRelation(deleteId);
		}
		//批量删除员工
		empMapper.batchDelete(batchList);
	}
}
