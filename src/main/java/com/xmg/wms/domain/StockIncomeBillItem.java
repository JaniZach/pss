package com.xmg.wms.domain;

import generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter@Getter
//采购入库单的清单
public class StockIncomeBillItem extends BaseDomain {
    private BigDecimal costPrice;
    private BigDecimal number;
    private BigDecimal amount;
    private String remark;
    //关联关系
    private Product product;
    //清单与订单是多对一,双向的关系(集合保存在one方)
    private StockIncomeBill bill;
}
