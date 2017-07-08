package com.xmg.wms.domain;

import generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter@Getter@ObjectProp("货品库存")
public class ProductStock extends BaseDomain {
    @ObjectProp("货品价格")
    private BigDecimal price;
    @ObjectProp("库存数量")
    private BigDecimal storeNumber;
    @ObjectProp("库存价值")
    private BigDecimal amount;
    //设置关联关系
    @ObjectProp("库存货品")
    private Product product;
    @ObjectProp("仓库")
    private Depot depot;
}
