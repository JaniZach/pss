package com.xmg.wms.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.xmg.wms.domain.Employee;
import com.xmg.wms.VO.SystemMenuVO;
import com.xmg.wms.util.UserContext;

import com.xmg.wms.domain.SystemMenu;
import com.xmg.wms.mapper.SystemMenuMapper;
import com.xmg.wms.page.PageResult;
import com.xmg.wms.query.SystemMenuQueryObject;
import com.xmg.wms.service.ISystemMenuService;
import lombok.Setter;
public class SystemMenuServiceImpl implements ISystemMenuService {
	@Setter
	private SystemMenuMapper systemMenuMapper;
	
	public void  delete(Long id) {
		  systemMenuMapper.delete(id);
	}

	public void save(SystemMenu entity) {
		  systemMenuMapper.save(entity);
	}

	public SystemMenu get(Long id) {
		return systemMenuMapper.get(id);
	}

	public List<SystemMenu> list() {
		return systemMenuMapper.list();
	}

	public void update(SystemMenu entity) {
		  systemMenuMapper.update(entity);
	}

	@Override
	public PageResult pageQuery(SystemMenuQueryObject qo) {
		Long count = systemMenuMapper.queryCount(qo);
		if(count<=0){
			return new PageResult(Collections.EMPTY_LIST,0, 1,qo.getPageSize());
		}
		List<SystemMenu> result = systemMenuMapper.queryList(qo);
		PageResult pageResult = new PageResult(result,count.intValue(),qo.getCurrentPage(),qo.getPageSize());
		return pageResult;
	}

	@Override
	public void batchDelete(List<Long> ids) {

		systemMenuMapper.batchDelete(ids);
	}

    @Override
    public List<SystemMenuVO> getParentMenusById(Long id) {
		List<SystemMenuVO> parentMenus = new ArrayList<>();
		SystemMenu parentMenu = systemMenuMapper.get(id);
		while(parentMenu!=null){
			SystemMenuVO vo = new SystemMenuVO();
			vo.setId(parentMenu.getId());
			vo.setName(parentMenu.getName());
			//注意获取到的parentMenus中vo的顺序,此刻是从子目录到父目录的顺序
			parentMenus.add(vo);
			//设置退出循环的条件
			parentMenu = parentMenu.getParent();
		}
		//反转parentMenus的顺序,此刻顺序是从父目录到子目录
		Collections.reverse(parentMenus);
		return parentMenus;
    }

    @Override
    public List<SystemMenu> queryMenusByParentSn(String parentSn) {
		//根据角色拥有的菜单权限返回对应的集合
		Employee employee = UserContext.getCurrentUser();
		//employee为null的判断,登陆拦截器已经处理
		if(employee.getAdmin()){
			//超级管理员放行
			return systemMenuMapper.queryMenusByParentSn(parentSn);
		}else{
			//普通用户根据其拥有的菜单权限放行
			return systemMenuMapper.queryMenusByParentSnAndPid(employee.getId(),parentSn);
		}
    }
}
