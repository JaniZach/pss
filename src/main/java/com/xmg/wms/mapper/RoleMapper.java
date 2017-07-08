package com.xmg.wms.mapper;

import org.apache.ibatis.annotations.Param;

import com.xmg.wms.domain.Role;

public interface RoleMapper extends BaseMapper<Role> {

	void saveRelation(@Param("rid") Long id,@Param("pid") Long id2);

	//删除权限和角色的关系
	void deletePermissionRelation(Long id);
	//删除菜单和角色的关系
	void deleteMenuRelation(Long id);
}
