package com.xmg.wms.web.action;

import com.xmg.wms.domain.Brand;
import com.xmg.wms.query.BrandQueryObject;
import com.xmg.wms.service.IBrandService;
import com.xmg.wms.util.RequiredPermission;

import lombok.Getter;
import lombok.Setter;

public class BrandAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private IBrandService brandService;

	@Getter
	private BrandQueryObject qo=new BrandQueryObject();

	@Getter
	private Brand brand = new Brand();
	
	@RequiredPermission("品牌管理列表")
	public String execute(){
		try {
			putContext("result", brandService.pageQuery(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return LIST;
	}

	@RequiredPermission("品牌管理编辑")
	public String input() {
		try {
			if (brand.getId() != null) {
                brand = brandService.get(brand.getId());
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return INPUT;
	}

	@RequiredPermission("品牌管理保存/更新")
	public String saveOrUpdate() {
		try {
			if (brand.getId() == null) {
                brandService.save(brand);
				addActionMessage("增加成功");
            } else {
                brandService.update(brand);
				addActionMessage("修改成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}

	@RequiredPermission("品牌管理删除")
	public String delete()  throws  Exception {
		try {
			if (brand.getId() != null) {
                brandService.delete(brand.getId());
				putMsg("删除成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg(e.getMessage());
		}
		return NONE;
	}
	@RequiredPermission("品牌管理批量删除")
	public String batchDelete() throws Exception {
		try {
			if (this.ids.size() != 0) {
				brandService.batchDelete(ids);
			putMsg("批量删除成功");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			addActionError(e1.getMessage());
		}
		return NONE;
	}
}
