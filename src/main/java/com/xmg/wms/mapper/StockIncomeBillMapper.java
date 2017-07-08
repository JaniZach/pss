package com.xmg.wms.mapper;

import com.xmg.wms.domain.StockIncomeBill;
import com.xmg.wms.domain.StockIncomeBillItem;
import com.xmg.wms.query.StockIncomeBillQueryObject;
import java.util.List;

public interface StockIncomeBillMapper extends BaseMapper<StockIncomeBill>{
    void saveItem(StockIncomeBillItem item);

    void deleteItem(Long id);

    void audit(StockIncomeBill bill);
    //此处写多表关系的处理,常规的方法已提取到BaseMapper
}