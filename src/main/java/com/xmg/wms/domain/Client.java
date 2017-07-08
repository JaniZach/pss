package com.xmg.wms.domain;

import generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter@ObjectProp("客户管理")
public class Client extends BaseDomain{
    @ObjectProp("客户名称")
    private String name;
    @ObjectProp("客户编码")
    private String sn;
    @ObjectProp("联系电话")
    private String phone;
}
