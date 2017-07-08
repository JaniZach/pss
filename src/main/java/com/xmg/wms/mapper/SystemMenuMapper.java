package com.xmg.wms.mapper;

import com.xmg.wms.domain.SystemMenu;
import com.xmg.wms.query.SystemMenuQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemMenuMapper extends BaseMapper<SystemMenu>{
    void saveRelation(@Param("rid") Long id, @Param("mid") Long id2);

    List<SystemMenu> queryMenusByParentSn(String parentSn);

    List<SystemMenu> queryMenusByParentSnAndPid(@Param("pId") Long id,@Param("pSn") String parentSn);
}