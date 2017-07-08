package com.xmg.wms.service;

import com.xmg.wms.domain.StockOutcomeBill;
import com.xmg.wms.page.PageResult;
import com.xmg.wms.query.StockOutcomeBillQueryObject;

import java.util.List;

public interface IStockOutcomeBillService {
	void delete(Long id);
	void save(StockOutcomeBill entity);
    StockOutcomeBill get(Long id);
    List<StockOutcomeBill> list();
	void update(StockOutcomeBill entity);
	PageResult pageQuery(StockOutcomeBillQueryObject qo);
	void batchDelete(List<Long> ids);

	void audit(StockOutcomeBill stockOutcomeBill);
}
