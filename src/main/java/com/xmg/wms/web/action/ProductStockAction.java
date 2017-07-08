package com.xmg.wms.web.action;

import com.xmg.wms.domain.ProductStock;
import com.xmg.wms.query.ProductStockQueryObject;
import com.xmg.wms.service.IBrandService;
import com.xmg.wms.service.IDepotService;
import com.xmg.wms.service.IProductStockService;
import com.xmg.wms.util.RequiredPermission;

import lombok.Getter;
import lombok.Setter;

public class ProductStockAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private IProductStockService productStockService;
	@Setter
	private IBrandService brandService;
	@Setter
	private IDepotService depotService;

	@Getter
	private ProductStockQueryObject qo=new ProductStockQueryObject();

	@Getter
	private ProductStock productStock = new ProductStock();
	
	@RequiredPermission("货品库存列表")
	public String execute(){
		try {
			//将仓库和品牌信息共享到页面
			this.clearMessages();//解决为设置limitNum值提示的信息
			putContext("depots",depotService.list());
			putContext("brands",brandService.list());
			putContext("result", productStockService.pageQuery(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return LIST;
	}

	@RequiredPermission("货品库存编辑")
	public String input() {
		try {
			if (productStock.getId() != null) {
                productStock = productStockService.get(productStock.getId());
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return INPUT;
	}

	@RequiredPermission("货品库存保存/更新")
	public String saveOrUpdate() {
		try {
			if (productStock.getId() == null) {
                productStockService.save(productStock);
				addActionMessage("增加成功");
            } else {
                productStockService.update(productStock);
				addActionMessage("修改成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}

	@RequiredPermission("货品库存删除")
	public String delete()  throws  Exception {
		try {
			if (productStock.getId() != null) {
                productStockService.delete(productStock.getId());
				putMsg("删除成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg(e.getMessage());
		}
		return NONE;
	}
	@RequiredPermission("货品库存批量删除")
	public String batchDelete() throws Exception {
		try {
			if (this.ids.size() != 0) {
		productStockService.batchDelete(ids);
			putMsg("批量删除成功");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			addActionError(e1.getMessage());
		}
		return NONE;
	}
}
