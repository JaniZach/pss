package com.xmg.wms.service;
import java.util.List;
import com.xmg.wms.domain.Supplier;
import com.xmg.wms.page.PageResult;
import com.xmg.wms.query.SupplierQueryObject;

public interface ISupplierService {
	void delete(Long id);
	void save(Supplier entity);
    Supplier get(Long id);
    List<Supplier> list();
	void update(Supplier entity);
	PageResult pageQuery(SupplierQueryObject qo);
	void batchDelete(List<Long> ids);
}
