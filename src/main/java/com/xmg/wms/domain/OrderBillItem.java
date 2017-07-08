package com.xmg.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter@Getter
//订单明细表
public class OrderBillItem extends BaseDomain{
    //成本价
    private BigDecimal costPrice;
    //数量
    private BigDecimal number;
    //金额小计
    private BigDecimal amount;
    //备注
    private String remark;
    //产品
    private Product product;
    //订单
    /**
     * 一个订单中包含多个订单明细,多对一的关系(集合在one方中)
     * 两者双向关联
     */
    private OrderBill bill;
}
