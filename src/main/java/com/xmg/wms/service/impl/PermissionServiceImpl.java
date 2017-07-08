package com.xmg.wms.service.impl;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.Setter;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.xmg.wms.domain.Permission;
import com.xmg.wms.mapper.PermissionMapper;
import com.xmg.wms.page.PageResult;
import com.xmg.wms.query.QueryObject;
import com.xmg.wms.service.IPermissionService;
import com.xmg.wms.util.RequiredPermission;
import com.xmg.wms.web.action.BaseAction;

public class PermissionServiceImpl implements IPermissionService,ApplicationContextAware {
	@Setter
	private PermissionMapper permissionMapper;
	private ApplicationContext ctx;
	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		this.ctx = ctx;
	}
	@Override
	public void save(Permission dept) {
		permissionMapper.save(dept);
	}

	@Override
	public void delete(Long id) {
		//删除权限的时候,也应该先删除关系表中相关数据,再删除权限
		permissionMapper.deleteRelation(id);
		permissionMapper.delete(id);
	}

	@Override
	public List<Permission> list() {
		return permissionMapper.list();
	}

	@Override
	public PageResult pageQuery(QueryObject qo) {
		Long totalCount = permissionMapper.queryCount(qo);
		if (totalCount == 0) {
			//当总记录数为0的时候,让当前页为1
			return new PageResult(Collections.EMPTY_LIST, 0,
					1, qo.getPageSize());
		}
		List<Permission> listData = permissionMapper.queryList(qo);
		return new PageResult(listData, totalCount.intValue(),
				qo.getCurrentPage(), qo.getPageSize());
	}

	@Override
	public void reload() {
		//去重操作
		List<Permission> list = permissionMapper.list();
		Set<String> permissionSet = new HashSet<>();
		for (Permission permission : list) {
			permissionSet.add(permission.getExpression());
		}
		//保存操作
		Map<String, BaseAction> actionMap = ctx.getBeansOfType(BaseAction.class);
		Collection<BaseAction> actions = actionMap.values();
		for (BaseAction action : actions) {
			String actionName = action.getClass().getName();
			Method[] methods = action.getClass().getDeclaredMethods();
			for (Method method : methods) {
				if(method.isAnnotationPresent(RequiredPermission.class)){
					RequiredPermission annotation = method.getAnnotation(RequiredPermission.class);
					String annotationName = annotation.value();
					String expression = actionName+":"+method.getName();
					//进行去重
					if(!permissionSet.contains(expression)){
						Permission p = new Permission();
						p.setName(annotationName);
						p.setExpression(expression);
						permissionMapper.save(p);
					}
				}
			}
		}
	}

	@Override
	public void batchDelete(List<Long> batchList) {
		//删除权限的时候,也应该先删除权限与角色关系表中相关数据,再删除权限
		for (Long deleteId : batchList) {
			permissionMapper.deleteRelation(deleteId);
		}
		permissionMapper.batchDelete(batchList);
	}
}
