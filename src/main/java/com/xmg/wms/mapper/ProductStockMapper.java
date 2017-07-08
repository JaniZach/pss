package com.xmg.wms.mapper;

import com.xmg.wms.domain.ProductStock;
import com.xmg.wms.query.ProductStockQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductStockMapper extends BaseMapper<ProductStock>{
    ProductStock queryByDepotIdAndProductId(@Param("depotId") Long depotId,@Param("productId") Long productId);
    //此处写多表关系的处理,常规的方法已提取到BaseMapper
}