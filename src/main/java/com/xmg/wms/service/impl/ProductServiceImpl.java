package com.xmg.wms.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.wms.domain.Product;
import com.xmg.wms.mapper.ProductMapper;
import com.xmg.wms.page.PageResult;
import com.xmg.wms.query.ProductQueryObject;
import com.xmg.wms.service.IProductService;
import lombok.Setter;
public class ProductServiceImpl implements IProductService {
	@Setter
	private ProductMapper productMapper;
	
	public void  delete(Long id) {
		  productMapper.delete(id);
	}

	public void save(Product entity) {
		  productMapper.save(entity);
	}

	public Product get(Long id) {
		return productMapper.get(id);
	}

	public List<Product> list() {
		return productMapper.list();
	}

	public void update(Product entity) {
		  productMapper.update(entity);
	}

	@Override
	public PageResult pageQuery(ProductQueryObject qo) {
		Long count = productMapper.queryCount(qo);
		if(count<=0){
			return new PageResult(Collections.EMPTY_LIST,0, 1,qo.getPageSize());
		}
		List<Product> result = productMapper.queryList(qo);
		PageResult pageResult = new PageResult(result,count.intValue(),qo.getCurrentPage(),qo.getPageSize());
		return pageResult;
	}
	@Override
	public void batchDelete(List<Long> ids) {
		//此处应注意外键约束的时候,应先删除关系表中相关数据
		//删除关系

		//删除关系
		productMapper.batchDelete(ids);
	}
}
