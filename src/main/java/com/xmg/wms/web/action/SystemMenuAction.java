package com.xmg.wms.web.action;

import com.xmg.wms.domain.SystemMenu;
import com.xmg.wms.VO.SystemMenuVO;
import com.xmg.wms.query.SystemMenuQueryObject;
import com.xmg.wms.service.ISystemMenuService;
import com.xmg.wms.util.RequiredPermission;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SystemMenuAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private ISystemMenuService systemMenuService;

	@Getter
	private SystemMenuQueryObject qo=new SystemMenuQueryObject();

	@Getter
	private SystemMenu systemMenu = new SystemMenu();
	@Getter
	private String parentName;
	@RequiredPermission("系统菜单列表")
	public String execute(){
		try {
			//根据传递过来的qo.parentId获取到简单的父目录集合信息
			List<SystemMenuVO> parentMenus = systemMenuService.getParentMenusById(qo.getParentId());
			putContext("parentMenus",parentMenus);
			//根据传递过来的qo.parentId,获取parentName
			putContext("result", systemMenuService.pageQuery(qo));

		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return LIST;
	}
	@RequiredPermission("系统菜单编辑")
	public String input() {
		try {
			//通过页面传过来的qo.parentId来找到parentMenu
			if(qo.getParentId()!=null&&qo.getParentId()>0){
				SystemMenu parentMenu = systemMenuService.get(qo.getParentId());
				putContext("parentName",parentMenu.getName());
			}else{
				putContext("parentName","根目录");
			}
			if (this.systemMenu.getId() != null) {
                this.systemMenu = systemMenuService.get(this.systemMenu.getId());
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return INPUT;
	}

	@RequiredPermission("系统菜单保存/更新")
	public String saveOrUpdate() {
		try {
			if(qo.getParentId()!=null){
				//前台传过来数据qo,根据qo.parentId获取parent,为systemMenu设置parent的值
				systemMenu.setParent(systemMenuService.get(qo.getParentId()));
			}
			if (systemMenu.getId() == null) {
                systemMenuService.save(systemMenu);
				addActionMessage("增加成功");
            } else {
                systemMenuService.update(systemMenu);
				addActionMessage("修改成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}

	@RequiredPermission("系统菜单删除")
	public String delete()  throws  Exception {
		try {
			if (systemMenu.getId() != null) {
                systemMenuService.delete(systemMenu.getId());
				putMsg("删除成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg(e.getMessage());
		}
		return NONE;
	}
	@RequiredPermission("系统菜单批量删除")
	public String batchDelete() throws Exception {
		try {
			if (this.ids.size() != 0) {
				systemMenuService.batchDelete(ids);
				putMsg("批量删除成功");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			addActionError(e1.getMessage());
		}
		return NONE;
	}
	public String queryMenusByParentSn() throws Exception{
		//根据父目录名称查询全部子目录
		List<SystemMenu> menus = systemMenuService.queryMenusByParentSn(qo.getParentSn());
		//将js所需的参数封装,保存到list集合中并传递回页面
		List<Map<String,Object>> list = new ArrayList<>();
		for (SystemMenu menu : menus) {
			Map<String, Object> map = menu.toJson();
			list.add(map);
		}
		//将值返回页面
		putJson(list);
		return NONE;
	}
}
