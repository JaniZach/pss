package com.xmg.wms.web.action;

import com.xmg.wms.domain.StockOutcomeBill;
import com.xmg.wms.query.StockOutcomeBillQueryObject;
import com.xmg.wms.service.IClientService;
import com.xmg.wms.service.IDepotService;
import com.xmg.wms.service.IStockOutcomeBillService;
import com.xmg.wms.util.RequiredPermission;
import lombok.Getter;
import lombok.Setter;

public class StockOutcomeBillAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private IStockOutcomeBillService stockOutcomeBillService;
	@Setter
	private IDepotService depotService;
	@Setter
	private IClientService clientService;

	@Getter
	private StockOutcomeBillQueryObject qo=new StockOutcomeBillQueryObject();

	@Getter
	private StockOutcomeBill stockOutcomeBill = new StockOutcomeBill();
	
	@RequiredPermission("采购入库单列表")
	public String execute(){
		try {
			//将全部仓库信息传递到页面
			putContext("depots",depotService.list());
			//将全部客户信息传递到页面
			putContext("clients",clientService.list());
			putContext("result", stockOutcomeBillService.pageQuery(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return LIST;
	}

	@RequiredPermission("采购入库单编辑")
	public String input() {
		try {
			//将全部仓库信息传递到页面
			putContext("depots",depotService.list());
			//将全部客户信息传递到页面
			putContext("clients",clientService.list());
			if (stockOutcomeBill.getId() != null) {
                stockOutcomeBill = stockOutcomeBillService.get(stockOutcomeBill.getId());
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return INPUT;
	}

	@RequiredPermission("采购入库单保存/更新")
	public String saveOrUpdate() {
		try {
			if (stockOutcomeBill.getId() == null) {
                stockOutcomeBillService.save(stockOutcomeBill);
				addActionMessage("增加成功");
            } else {
                stockOutcomeBillService.update(stockOutcomeBill);
				addActionMessage("修改成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}

	@RequiredPermission("采购入库单删除")
	public String delete()  throws  Exception {
		try {
			if (stockOutcomeBill.getId() != null) {
                stockOutcomeBillService.delete(stockOutcomeBill.getId());
				putMsg("删除成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg(e.getMessage());
		}
		return NONE;
	}
	@RequiredPermission("采购入库单批量删除")
	public String batchDelete() throws Exception {
		try {
			if (this.ids.size() != 0) {
		stockOutcomeBillService.batchDelete(ids);
			putMsg("批量删除成功");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			addActionError(e1.getMessage());
		}
		return NONE;
	}
	@RequiredPermission("采购入库单审核")
	public String audit()  throws  Exception {
		try {
			if (stockOutcomeBill.getId() != null) {
				stockOutcomeBillService.audit(stockOutcomeBillService.get(stockOutcomeBill.getId()));
				putMsg("审核成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			putMsg(e.getMessage());
		}
		return NONE;
	}
	@RequiredPermission("采购入库单查看")
	public String show() {
		try {
			if (stockOutcomeBill.getId() != null) {
				stockOutcomeBill = stockOutcomeBillService.get(stockOutcomeBill.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "show";
	}
}
