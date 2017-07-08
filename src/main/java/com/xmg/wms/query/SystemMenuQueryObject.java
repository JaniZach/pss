package com.xmg.wms.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter@Getter@ToString
public class SystemMenuQueryObject extends QueryObject {
    //父目录,默认值为-1,代表根目录
    private Long parentId = -1L;
    //根据父目录名字查询得到全部子节点
    private String parentSn;
}
