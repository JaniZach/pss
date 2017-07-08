package com.xmg.wms.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.xmg.wms.domain.Department;
import com.xmg.wms.page.PageResult;
import com.xmg.wms.query.DepartmentQueryObject;
import com.xmg.wms.service.IDepartmentService;
import com.xmg.wms.util.RequiredPermission;
import lombok.Getter;
import lombok.Setter;


public class DepartmentAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Setter
	private IDepartmentService deptService;
	@Getter
	private Department dept = new Department();
	@Getter
	private DepartmentQueryObject qo = new DepartmentQueryObject();
	@Override
	@RequiredPermission("部门列表")
	public String execute() throws Exception {
		try {
			//int i = 1/0;
			PageResult result = deptService.pageQuery(qo);
			putContext("result",result);
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return LIST;
	}
	@RequiredPermission("部门删除")
	public String delete() throws Exception {
		try {
			if(dept.getId()!=null){
                deptService.delete(dept.getId());
                putMsg("删除成功!");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return NONE;
	}
	@RequiredPermission("部门编辑")
	public String input() throws Exception {
		try {
			if(dept.getId()!=null){
                dept=deptService.get(dept.getId());
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return INPUT;
	}
	@RequiredPermission("部门更新或保存")
	public String saveOrUpdate() throws Exception {
		try {
			if(dept.getId()!=null){
                deptService.update(dept);
                addActionMessage("修改成功!");
            }else{
                deptService.save(dept);
                addActionMessage("保存成功!");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}
	@RequiredPermission("部门批量删除操作")
	public String batchDelete() throws Exception {
		try {
			if (this.ids.size() != 0) {
				deptService.batchDelete(ids);
				putMsg("批量删除成功");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			addActionError(e1.getMessage());
		}
		return NONE;
	}
}
