package com.xmg.wms.mapper;

import com.xmg.wms.domain.Product;
import com.xmg.wms.query.ProductQueryObject;
import java.util.List;

public interface ProductMapper extends BaseMapper<Product>{
	//此处写多表关系的处理,常规的方法已提取到BaseMapper
}