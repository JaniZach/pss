package com.xmg.wms.service;
import java.util.List;
import com.xmg.wms.domain.OrderBill;
import com.xmg.wms.page.PageResult;
import com.xmg.wms.query.OrderBillQueryObject;

public interface IOrderBillService {
	void delete(Long id);
	void save(OrderBill entity);
    OrderBill get(Long id);
    List<OrderBill> list();
	void update(OrderBill entity);
	PageResult pageQuery(OrderBillQueryObject qo);
	void batchDelete(List<Long> ids);

    void audit(OrderBill orderBill);
}
