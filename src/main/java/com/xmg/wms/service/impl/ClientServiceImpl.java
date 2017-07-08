package com.xmg.wms.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.wms.domain.Client;
import com.xmg.wms.mapper.ClientMapper;
import com.xmg.wms.page.PageResult;
import com.xmg.wms.query.ClientQueryObject;
import com.xmg.wms.service.IClientService;
import lombok.Setter;
public class ClientServiceImpl implements IClientService {
	@Setter
	private ClientMapper clientMapper;
	
	public void  delete(Long id) {
		  clientMapper.delete(id);
	}

	public void save(Client entity) {
		  clientMapper.save(entity);
	}

	public Client get(Long id) {
		return clientMapper.get(id);
	}

	public List<Client> list() {
		return clientMapper.list();
	}

	public void update(Client entity) {
		  clientMapper.update(entity);
	}

	@Override
	public PageResult pageQuery(ClientQueryObject qo) {
		Long count = clientMapper.queryCount(qo);
		if(count<=0){
			return new PageResult(Collections.EMPTY_LIST,0, 1,qo.getPageSize());
		}
		List<Client> result = clientMapper.queryList(qo);
		PageResult pageResult = new PageResult(result,count.intValue(),qo.getCurrentPage(),qo.getPageSize());
		return pageResult;
	}
	@Override
	public void batchDelete(List<Long> ids) {
		//此处应注意外键约束的时候,应先删除关系表中相关数据
		//删除关系

		//删除关系
		clientMapper.batchDelete(ids);
	}
}
