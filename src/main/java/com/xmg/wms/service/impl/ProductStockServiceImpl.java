package com.xmg.wms.service.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.xmg.wms.domain.Depot;
import com.xmg.wms.domain.Product;
import com.xmg.wms.domain.StockOutcomeBill;
import com.xmg.wms.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.wms.domain.ProductStock;
import com.xmg.wms.mapper.ProductStockMapper;
import com.xmg.wms.page.PageResult;
import com.xmg.wms.query.ProductStockQueryObject;
import com.xmg.wms.service.IProductStockService;
import lombok.Setter;
public class ProductStockServiceImpl implements IProductStockService {
	@Setter
	private ProductStockMapper productStockMapper;
	
	public void  delete(Long id) {
		  productStockMapper.delete(id);
	}

	public void save(ProductStock entity) {
		  productStockMapper.save(entity);
	}

	public ProductStock get(Long id) {
		return productStockMapper.get(id);
	}

	public List<ProductStock> list() {
		return productStockMapper.list();
	}

	public void update(ProductStock entity) {
		  productStockMapper.update(entity);
	}

	@Override
	public PageResult pageQuery(ProductStockQueryObject qo) {
		Long count = productStockMapper.queryCount(qo);
		if(count<=0){
			return new PageResult(Collections.EMPTY_LIST,0, 1,qo.getPageSize());
		}
		List<ProductStock> result = productStockMapper.queryList(qo);
		PageResult pageResult = new PageResult(result,count.intValue(),qo.getCurrentPage(),qo.getPageSize());
		return pageResult;
	}
	@Override
	public void batchDelete(List<Long> ids) {
		//此处应注意外键约束的时候,应先删除关系表中相关数据
		//删除关系

		//删除关系
		productStockMapper.batchDelete(ids);
	}
	@Override
	public void income(Depot depot,Product product,BigDecimal storeNumber,BigDecimal costPrice){
		//入库方法的抽取
		ProductStock ps = productStockMapper.queryByDepotIdAndProductId(depot.getId(),product.getId());
		if(ps==null){
			//此时,库存表中没有相关信息,进行保存操作
			ps = new ProductStock();
			ps.setProduct(product);//产品信息
			ps.setDepot(depot);//仓库信息
			ps.setPrice(costPrice);//成本价
			ps.setStoreNumber(storeNumber);//货存量
			ps.setAmount(costPrice.multiply(storeNumber)
					.setScale(2,BigDecimal.ROUND_HALF_UP));//货存价值
			productStockMapper.save(ps);
		}else{
			//此时,库存表中有相关信息,进行更新操作
			ps.setProduct(product);//产品信息
			ps.setStoreNumber(ps.getStoreNumber().add(storeNumber));//入库数量
			ps.setAmount(ps.getAmount().add(costPrice.multiply(storeNumber)//入库金额小计
					.setScale(2,BigDecimal.ROUND_HALF_UP)));
			ps.setPrice(ps.getAmount().divide(ps.getStoreNumber(),2, BigDecimal.ROUND_HALF_UP));//库存产品单价
			productStockMapper.update(ps);
		}
	};

	@Override
	public BigDecimal outcome(Depot depot,Product product,BigDecimal saleNumber){
		//出库方法的抽取
		ProductStock ps = productStockMapper.queryByDepotIdAndProductId(depot.getId(),
				product.getId());
		if(ps==null){
			//此时,没有库存,提示用户
			throw new RuntimeException(depot.getName()+"中 ["+product.getName()+"] 没有库存!");
		}
		if(ps.getStoreNumber().compareTo(saleNumber)<0){
			//此时,有库存,但库存量不足
			throw new RuntimeException(depot.getName()+"中["+product.getName()+"]仅剩 "
					+ps.getStoreNumber()+" 件,还缺货 "+saleNumber
					.subtract(ps.getStoreNumber())+" 件");
		}
		//库存量足够时,进行出库.库存量减少,库存价值减少
		ps.setStoreNumber(ps.getStoreNumber().subtract(saleNumber));
		ps.setAmount(ps.getPrice().multiply(ps.getStoreNumber())
				.setScale(2,BigDecimal.ROUND_HALF_UP));
		//更新库存信息
		productStockMapper.update(ps);
		//这里返回的库存价格是 销售单 中所需要的
		return ps.getPrice();
	};
}
