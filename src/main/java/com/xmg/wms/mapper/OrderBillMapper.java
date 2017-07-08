package com.xmg.wms.mapper;

import com.xmg.wms.domain.OrderBill;
import com.xmg.wms.domain.OrderBillItem;

import java.util.List;

public interface OrderBillMapper extends BaseMapper<OrderBill>{
	//此处写多表关系的处理,常规的方法已提取到BaseMapper
    List<OrderBillItem> getItemsByBillId(Long id);

    void saveItem(OrderBillItem item);

    void deleteItem(Long id);

    void audit(OrderBill orderBill);
}