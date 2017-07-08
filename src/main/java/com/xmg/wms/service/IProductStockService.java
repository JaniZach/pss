package com.xmg.wms.service;

import com.xmg.wms.domain.Depot;
import com.xmg.wms.domain.Product;
import com.xmg.wms.domain.ProductStock;
import com.xmg.wms.page.PageResult;
import com.xmg.wms.query.ProductStockQueryObject;

import java.math.BigDecimal;
import java.util.List;

public interface IProductStockService {
	void delete(Long id);
	void save(ProductStock entity);
    ProductStock get(Long id);
    List<ProductStock> list();
	void update(ProductStock entity);
	PageResult pageQuery(ProductStockQueryObject qo);
	void batchDelete(List<Long> ids);

	/**
	 * @param depot 仓库
	 * @param product 产品
	 * @param storeNumber 入库数量
	 * @param costPrice 成本价
	 */
    void income(Depot depot,Product product,BigDecimal storeNumber,BigDecimal costPrice);

	/**
	 *
	 * @param depot 仓库
	 * @param product 产品
	 * @param saleNumber 出库数量
	 */
	BigDecimal outcome(Depot depot,Product product,BigDecimal saleNumber);
}
