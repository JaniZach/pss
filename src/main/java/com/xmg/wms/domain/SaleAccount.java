package com.xmg.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter@Getter//销售账实体对象
public class SaleAccount extends BaseDomain{
    private Date vdate;//销售时间
    private BigDecimal number;//销售数量
    private BigDecimal costPrice;//成本价
    private BigDecimal costAmount;//成本小计
    private BigDecimal salePrice;//销售价格
    private BigDecimal saleAmount;//销售值小计
    //关联关系
    private Product product;//产品
    private Employee saleman;//销售人员
    private Client client;//客户
}
