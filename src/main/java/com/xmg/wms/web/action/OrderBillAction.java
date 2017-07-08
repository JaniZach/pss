package com.xmg.wms.web.action;

import com.xmg.wms.domain.OrderBill;
import com.xmg.wms.query.OrderBillQueryObject;
import com.xmg.wms.service.IOrderBillService;
import com.xmg.wms.service.ISupplierService;
import com.xmg.wms.util.RequiredPermission;

import lombok.Getter;
import lombok.Setter;

public class OrderBillAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private IOrderBillService orderBillService;
	@Setter
	private ISupplierService supplierService;

	@Getter
	private OrderBillQueryObject qo=new OrderBillQueryObject();

	@Getter
	private OrderBill orderBill = new OrderBill();
	
	@RequiredPermission("采购订单列表")
	public String execute(){
		try {
			//共享全部供应商信息到context区域
			putContext("suppliers",supplierService.list());
			putContext("result", orderBillService.pageQuery(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return LIST;
	}

	@RequiredPermission("采购订单编辑")
	public String input() {
		try {
			//共享全部供应商信息到context区域
			putContext("suppliers",supplierService.list());
			if (orderBill.getId() != null) {
                orderBill = orderBillService.get(orderBill.getId());
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return INPUT;
	}

	@RequiredPermission("采购订单保存/更新")
	public String saveOrUpdate() {
		try {
			if (orderBill.getId() == null) {
                orderBillService.save(orderBill);
				addActionMessage("增加成功");
            } else {
                orderBillService.update(orderBill);
				addActionMessage("修改成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}

	@RequiredPermission("采购订单删除")
	public String delete()  throws  Exception {
		try {
			if (orderBill.getId() != null) {
                orderBillService.delete(orderBill.getId());
				putMsg("删除成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg(e.getMessage());
		}
		return NONE;
	}
	@RequiredPermission("采购订单批量删除")
	public String batchDelete() throws Exception {
		try {
			if (this.ids.size() != 0) {
		orderBillService.batchDelete(ids);
			putMsg("批量删除成功");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			addActionError(e1.getMessage());
		}
		return NONE;
	}
	@RequiredPermission("采购订单查看")
	public String show() {
		try {
			//共享全部供应商信息到context区域
			putContext("suppliers",supplierService.list());
			if (orderBill.getId() != null) {
				orderBill = orderBillService.get(orderBill.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "show";
	}
	@RequiredPermission("采购订单审核")
	public String audit() throws Exception {
		try {
			if (orderBill.getId() != null) {
				//根据id获取表单对象
				orderBill = orderBillService.get(orderBill.getId());
				//表单的审核
				orderBillService.audit(orderBill);
				putMsg("审核成功! ");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			addActionError(e1.getMessage());
		}
		return NONE;
	}
}
