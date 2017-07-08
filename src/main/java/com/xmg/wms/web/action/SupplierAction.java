package com.xmg.wms.web.action;

import com.xmg.wms.domain.Supplier;
import com.xmg.wms.query.SupplierQueryObject;
import com.xmg.wms.service.ISupplierService;
import com.xmg.wms.util.RequiredPermission;

import lombok.Getter;
import lombok.Setter;

public class SupplierAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private ISupplierService supplierService;

	@Getter
	private SupplierQueryObject qo=new SupplierQueryObject();

	@Getter
	private Supplier supplier = new Supplier();
	
	@RequiredPermission("供应商管理列表")
	public String execute(){
		try {
			putContext("result", supplierService.pageQuery(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return LIST;
	}

	@RequiredPermission("供应商管理编辑")
	public String input() {
		try {
			if (supplier.getId() != null) {
                supplier = supplierService.get(supplier.getId());
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return INPUT;
	}

	@RequiredPermission("供应商管理保存/更新")
	public String saveOrUpdate() {
		try {
			if (supplier.getId() == null) {
                supplierService.save(supplier);
				addActionMessage("增加成功");
            } else {
                supplierService.update(supplier);
				addActionMessage("修改成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}

	@RequiredPermission("供应商管理删除")
	public String delete()  throws  Exception {
		try {
			if (supplier.getId() != null) {
                supplierService.delete(supplier.getId());
				putMsg("删除成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg(e.getMessage());
		}
		return NONE;
	}
	@RequiredPermission("供应商管理批量删除")
	public String batchDelete() throws Exception {
		try {
			if (this.ids.size() != 0) {
		supplierService.batchDelete(ids);
			putMsg("批量删除成功");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			addActionError(e1.getMessage());
		}
		return NONE;
	}
}
