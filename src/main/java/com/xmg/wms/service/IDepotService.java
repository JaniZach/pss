package com.xmg.wms.service;
import java.util.List;
import com.xmg.wms.domain.Depot;
import com.xmg.wms.page.PageResult;
import com.xmg.wms.query.DepotQueryObject;

public interface IDepotService {
	void delete(Long id);
	void save(Depot entity);
    Depot get(Long id);
    List<Depot> list();
	void update(Depot entity);
	PageResult pageQuery(DepotQueryObject qo);
	void batchDelete(List<Long> ids);
}
