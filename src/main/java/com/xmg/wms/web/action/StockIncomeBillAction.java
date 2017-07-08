package com.xmg.wms.web.action;

import com.xmg.wms.domain.StockIncomeBill;
import com.xmg.wms.query.StockIncomeBillQueryObject;
import com.xmg.wms.service.IDepotService;
import com.xmg.wms.service.IStockIncomeBillService;
import com.xmg.wms.util.RequiredPermission;

import lombok.Getter;
import lombok.Setter;

public class StockIncomeBillAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private IStockIncomeBillService stockIncomeBillService;
	@Setter
	private IDepotService depotService;

	@Getter
	private StockIncomeBillQueryObject qo=new StockIncomeBillQueryObject();

	@Getter
	private StockIncomeBill stockIncomeBill = new StockIncomeBill();
	
	@RequiredPermission("采购入库单列表")
	public String execute(){
		try {
			//将全部仓库信息传递到页面
			putContext("depots",depotService.list());
			putContext("result", stockIncomeBillService.pageQuery(qo));
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
			if (stockIncomeBill.getId() != null) {
                stockIncomeBill = stockIncomeBillService.get(stockIncomeBill.getId());
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
			if (stockIncomeBill.getId() == null) {
                stockIncomeBillService.save(stockIncomeBill);
				addActionMessage("增加成功");
            } else {
                stockIncomeBillService.update(stockIncomeBill);
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
			if (stockIncomeBill.getId() != null) {
                stockIncomeBillService.delete(stockIncomeBill.getId());
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
		stockIncomeBillService.batchDelete(ids);
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
			if (stockIncomeBill.getId() != null) {
				stockIncomeBillService.audit(stockIncomeBillService.get(stockIncomeBill.getId()));
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
			if (stockIncomeBill.getId() != null) {
				stockIncomeBill = stockIncomeBillService.get(stockIncomeBill.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "show";
	}
}
