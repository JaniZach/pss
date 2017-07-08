package com.xmg.wms.mapper;

import com.xmg.wms.domain.StockOutcomeBill;
import com.xmg.wms.domain.StockOutcomeBillItem;

public interface StockOutcomeBillMapper extends BaseMapper<StockOutcomeBill>{
    void saveItem(StockOutcomeBillItem item);

    void deleteItem(Long id);

    void audit(StockOutcomeBill bill);
    //此处写多表关系的处理,常规的方法已提取到BaseMapper
}