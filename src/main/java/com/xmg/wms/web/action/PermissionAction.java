package com.xmg.wms.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.xmg.wms.domain.Permission;
import com.xmg.wms.page.PageResult;
import com.xmg.wms.query.PermissionQueryObject;
import com.xmg.wms.service.IPermissionService;
import com.xmg.wms.util.RequiredPermission;
import lombok.Getter;
import lombok.Setter;
public class PermissionAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Setter
	private IPermissionService permissionService;
	@Getter
	private PermissionQueryObject qo = new PermissionQueryObject();
	@Getter
	private Permission p = new Permission();
	@Override
	@RequiredPermission("权限列表")
	public String execute() throws Exception {
		PageResult result = permissionService.pageQuery(qo);
		ActionContext.getContext().put("result",result);
		return LIST;
	}
	@RequiredPermission("权限删除")
	public String delete() throws Exception {
		if(p.getId()!=null){
			permissionService.delete(p.getId());
			putMsg("删除成功");
		}
		return NONE;
	}
	@RequiredPermission("权限重加载")
	public String reload() throws Exception {
		permissionService.reload();
		return NONE;
	}
	@RequiredPermission("权限批量删除操作")
	public String batchDelete() throws Exception {
		try {
			if (this.ids.size() != 0) {
				permissionService.batchDelete(ids);
				putMsg("批量删除成功");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			addActionError(e1.getMessage());
		}
		return NONE;
	}
}
