package com.xmg.wms.web.action;

import com.xmg.wms.domain.Depot;
import com.xmg.wms.query.DepotQueryObject;
import com.xmg.wms.service.IDepotService;
import com.xmg.wms.util.RequiredPermission;

import lombok.Getter;
import lombok.Setter;

public class DepotAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private IDepotService depotService;

	@Getter
	private DepotQueryObject qo=new DepotQueryObject();

	@Getter
	private Depot depot = new Depot();
	
	@RequiredPermission("仓库管理列表")
	public String execute(){
		try {
			putContext("result", depotService.pageQuery(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return LIST;
	}

	@RequiredPermission("仓库管理编辑")
	public String input() {
		try {
			if (depot.getId() != null) {
                depot = depotService.get(depot.getId());
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return INPUT;
	}

	@RequiredPermission("仓库管理保存/更新")
	public String saveOrUpdate() {
		try {
			if (depot.getId() == null) {
                depotService.save(depot);
				addActionMessage("增加成功");
            } else {
                depotService.update(depot);
				addActionMessage("修改成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}

	@RequiredPermission("仓库管理删除")
	public String delete()  throws  Exception {
		try {
			if (depot.getId() != null) {
                depotService.delete(depot.getId());
				putMsg("删除成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg(e.getMessage());
		}
		return NONE;
	}
	@RequiredPermission("仓库管理批量删除")
	public String batchDelete() throws Exception {
		try {
			if (this.ids.size() != 0) {
		depotService.batchDelete(ids);
			putMsg("批量删除成功");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			addActionError(e1.getMessage());
		}
		return NONE;
	}
}
