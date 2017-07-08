package com.xmg.wms.mapper;

import com.xmg.wms.domain.Permission;

public interface PermissionMapper extends BaseMapper<Permission> {

	void deleteRelation(Long id);
}
