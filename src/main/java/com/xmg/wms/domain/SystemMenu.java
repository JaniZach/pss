package com.xmg.wms.domain;

import generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ObjectProp("系统菜单")
@Setter
@Getter
public class SystemMenu extends BaseDomain {
    @ObjectProp("菜单名称")
    private String name;
    @ObjectProp("URL")
    private String url;
    @ObjectProp("菜单编码")
    private String sn;
    //关联父菜单
    @ObjectProp("上级菜单")
    private SystemMenu parent;
    //关联所有子菜单
    private List<SystemMenu> children;

    public String getParentName() {
        //由于延迟加载的存在,此处的判断条件不可以使用parent==null,而应使用this.getParent()==null
        return this.getParent() == null ? "根目录" : parent.getName();
    }

    //需要id,pId,name,action等信息,进行疯传
    public Map<String,Object> toJson(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",this.getId());
        map.put("pId",this.getParent().getId());
        map.put("name",this.getName());
        map.put("action",this.getUrl());
        return map;
    }
    @Override
    public String toString() {
        return "SystemMenu{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", sn='" + sn + '\'' +
                ", parent=" + parent +
                '}';
    }
}
