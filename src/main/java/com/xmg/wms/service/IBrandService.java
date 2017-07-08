package com.xmg.wms.service;
import java.util.List;
import com.xmg.wms.domain.Brand;
import com.xmg.wms.page.PageResult;
import com.xmg.wms.query.BrandQueryObject;

public interface IBrandService {
	void delete(Long id);
	void save(Brand entity);
    Brand get(Long id);
    List<Brand> list();
	void update(Brand entity);
	PageResult pageQuery(BrandQueryObject qo);
	void batchDelete(List<Long> ids);
}
