package com.xmg.wms.service;
import java.util.List;
import com.xmg.wms.domain.StockIncomeBill;
import com.xmg.wms.page.PageResult;
import com.xmg.wms.query.StockIncomeBillQueryObject;

public interface IStockIncomeBillService {
	void delete(Long id);
	void save(StockIncomeBill entity);
    StockIncomeBill get(Long id);
    List<StockIncomeBill> list();
	void update(StockIncomeBill entity);
	PageResult pageQuery(StockIncomeBillQueryObject qo);
	void batchDelete(List<Long> ids);

	void audit(StockIncomeBill stockIncomeBill);
}
