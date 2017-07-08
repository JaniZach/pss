package com.xmg.wms.service;
import java.util.List;
import com.xmg.wms.domain.Client;
import com.xmg.wms.page.PageResult;
import com.xmg.wms.query.ClientQueryObject;

public interface IClientService {
	void delete(Long id);
	void save(Client entity);
    Client get(Long id);
    List<Client> list();
	void update(Client entity);
	PageResult pageQuery(ClientQueryObject qo);
	void batchDelete(List<Long> ids);
}
