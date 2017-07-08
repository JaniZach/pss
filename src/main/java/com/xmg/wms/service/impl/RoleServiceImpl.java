package com.xmg.wms.service.impl;

import java.util.Collections;
import java.util.List;

import com.xmg.wms.domain.Permission;
import com.xmg.wms.domain.Role;
import com.xmg.wms.domain.SystemMenu;
import com.xmg.wms.mapper.RoleMapper;
import com.xmg.wms.mapper.SystemMenuMapper;
import com.xmg.wms.page.PageResult;
import com.xmg.wms.query.QueryObject;
import com.xmg.wms.service.IRoleService;

import lombok.Setter;

public class RoleServiceImpl implements IRoleService {
	@Setter
	private RoleMapper roleMapper;
	@Setter
	private SystemMenuMapper systemMenuMapper;

	@Override
	public void save(Role r) {
		//保存角色的同时,需要将其对应的权限保存--保存到关系表中
		roleMapper.save(r);
		List<Permission> permissions = r.getPermissions();
		for (Permission p : permissions) {
			roleMapper.saveRelation(r.getId(),p.getId());
		}
		//保存角色的同时,需要将其对应的菜单保存--保存到关系表中
		List<SystemMenu> menus = r.getMenus();
		for (SystemMenu menu : menus) {
			systemMenuMapper.saveRelation(r.getId(),menu.getId());
		}
	}

	@Override
	public void delete(Long id) {
		//进行删除操作的时候,也应该先删除关系,再删除角色
		roleMapper.deletePermissionRelation(id);
		roleMapper.deleteMenuRelation(id);
		roleMapper.delete(id);
	}
	
	@Override
	public void update(Role r) {
		//进行更新的时候,需要先将所有的权限关系删除,再重新添加权限关系
		roleMapper.deletePermissionRelation(r.getId());
		List<Permission> permissions = r.getPermissions();
		for (Permission p : permissions) {
			roleMapper.saveRelation(r.getId(),p.getId());
		}
		roleMapper.deleteMenuRelation(r.getId());
		List<SystemMenu> menus = r.getMenus();
		for (SystemMenu menu : menus) {
			systemMenuMapper.saveRelation(r.getId(),menu.getId());
		}
		roleMapper.update(r);	
	}
	
	@Override
	public Role get(Long id) {
		return roleMapper.get(id);
	}

	@Override
	public List<Role> list() {
		return roleMapper.list();
	}

	@Override
	public PageResult pageQuery(QueryObject qo) {
		Long totalCount = roleMapper.queryCount(qo);
		if (totalCount == 0) {
			//当总记录数为0的时候,设置当前页为1
			return new PageResult(Collections.EMPTY_LIST, 0,
					1, qo.getPageSize());
		}
		List<Role> listData = roleMapper.queryList(qo);
		return new PageResult(listData, totalCount.intValue(),
				qo.getCurrentPage(), qo.getPageSize());
	}

	@Override
	public void batchDelete(List<Long> ids) {
		//进行删除操作的时候,也应该先删除关系,再删除角色
		for (Long id : ids) {
			roleMapper.deletePermissionRelation(id);
			roleMapper.deleteMenuRelation(id);
		}
		roleMapper.batchDelete(ids);
	}
}
