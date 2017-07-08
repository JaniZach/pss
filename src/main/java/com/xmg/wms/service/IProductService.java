package com.xmg.wms.service;
import java.util.List;
import com.xmg.wms.domain.Product;
import com.xmg.wms.page.PageResult;
import com.xmg.wms.query.ProductQueryObject;

public interface IProductService {
	void delete(Long id);
	void save(Product entity);
    Product get(Long id);
    List<Product> list();
	void update(Product entity);
	PageResult pageQuery(ProductQueryObject qo);
	void batchDelete(List<Long> ids);
}
