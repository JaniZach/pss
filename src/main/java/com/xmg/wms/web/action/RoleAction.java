package com.xmg.wms.web.action;

import java.util.List;

import com.xmg.wms.domain.SystemMenu;
import com.xmg.wms.service.ISystemMenuService;
import lombok.Getter;
import lombok.Setter;

import com.opensymphony.xwork2.ActionContext;
import com.xmg.wms.domain.Permission;
import com.xmg.wms.domain.Role;
import com.xmg.wms.page.PageResult;
import com.xmg.wms.query.QueryObject;
import com.xmg.wms.service.IPermissionService;
import com.xmg.wms.service.IRoleService;
import com.xmg.wms.util.RequiredPermission;



public class RoleAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Setter
	private IRoleService roleService;
	@Setter
	private IPermissionService permissionService;
	@Setter
	private ISystemMenuService systemMenuService;
	@Getter
	private Role role = new Role();
	@Getter
	private QueryObject qo = new QueryObject();
	@Override
	@RequiredPermission("角色列表")
	public String execute() throws Exception {
		PageResult result = roleService.pageQuery(qo);
		ActionContext.getContext().put("result", result);
		return LIST;
	}
	@RequiredPermission("角色删除")
	public String delete() throws Exception {
		if(role.getId()!=null){
			roleService.delete(role.getId());
			putMsg("删除成功!");
		}
		return NONE;
	}
	@RequiredPermission("角色编辑")
	public String input() throws Exception {
		if(role.getId()!=null){
			role = roleService.get(role.getId());
		}
		//将全部权限信息共享
		List<Permission> permissions = permissionService.list();
		List<SystemMenu> menus = systemMenuService.list();
		//将全部菜单信息共享
		putContext("menus",menus);
		putContext("permissions",permissions);
		return INPUT;
	}
	@RequiredPermission("角色保存或更新")
	public String saveOrUpdate() throws Exception {
		if(role.getId()!=null){
			roleService.update(role);
			addActionMessage("修改成功!");
		}else{
			roleService.save(role);
			//由于是先请求转发到roleAction_saveOrUpdate,再重定向到roleAction,
			//使用addActionMessage()方法,利用store拦截器,存储传递的信息
			addActionMessage("保存成功!");
		}
		return SUCCESS;
	}
	@RequiredPermission("角色批量删除")
	public String batchDelete() throws Exception {
		try {
			if (this.ids.size() != 0) {
				roleService.batchDelete(ids);
				//ajax请求,回复信息使用putMsg(),及请求响应
				putMsg("批量删除成功!");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			addActionError(e1.getMessage());
		}
		return NONE;
	}
}
