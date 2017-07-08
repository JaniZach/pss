package com.xmg.wms.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.wms.domain.Depot;
import com.xmg.wms.mapper.DepotMapper;
import com.xmg.wms.page.PageResult;
import com.xmg.wms.query.DepotQueryObject;
import com.xmg.wms.service.IDepotService;
import lombok.Setter;
public class DepotServiceImpl implements IDepotService {
	@Setter
	private DepotMapper depotMapper;
	
	public void  delete(Long id) {
		  depotMapper.delete(id);
	}

	public void save(Depot entity) {
		  depotMapper.save(entity);
	}

	public Depot get(Long id) {
		return depotMapper.get(id);
	}

	public List<Depot> list() {
		return depotMapper.list();
	}

	public void update(Depot entity) {
		  depotMapper.update(entity);
	}

	@Override
	public PageResult pageQuery(DepotQueryObject qo) {
		Long count = depotMapper.queryCount(qo);
		if(count<=0){
			return new PageResult(Collections.EMPTY_LIST,0, 1,qo.getPageSize());
		}
		List<Depot> result = depotMapper.queryList(qo);
		PageResult pageResult = new PageResult(result,count.intValue(),qo.getCurrentPage(),qo.getPageSize());
		return pageResult;
	}
	@Override
	public void batchDelete(List<Long> ids) {
		//此处应注意外键约束的时候,应先删除关系表中相关数据
		//删除关系

		//删除关系
		depotMapper.batchDelete(ids);
	}
}
