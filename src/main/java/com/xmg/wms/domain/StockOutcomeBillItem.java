package com.xmg.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter@Getter
//销售出库单的清单
public class StockOutcomeBillItem extends BaseDomain {
    //销售价格
    private BigDecimal salePrice;
    //销售数量
    private BigDecimal number;
    //金额小计
    private BigDecimal amount;
    //备注
    private String remark;
    //关联关系
    private Product product;
    //清单与订单是多对一,双向的关系(集合保存在one方)
    private StockOutcomeBill bill;
}
