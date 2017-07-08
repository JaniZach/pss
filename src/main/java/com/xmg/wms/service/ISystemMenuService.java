package com.xmg.wms.service;
import java.util.List;
import com.xmg.wms.domain.SystemMenu;
import com.xmg.wms.VO.SystemMenuVO;
import com.xmg.wms.page.PageResult;
import com.xmg.wms.query.SystemMenuQueryObject;

public interface ISystemMenuService {
	void delete(Long id);
	void save(SystemMenu entity);
    SystemMenu get(Long id);
    List<SystemMenu> list();
	void update(SystemMenu entity);
	PageResult pageQuery(SystemMenuQueryObject qo);
	void batchDelete(List<Long> ids);
	//查询父目录的简单信息
	List<SystemMenuVO> getParentMenusById(Long id);

    List<SystemMenu> queryMenusByParentSn(String parentSn);
}
