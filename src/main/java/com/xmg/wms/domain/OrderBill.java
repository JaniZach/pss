package com.xmg.wms.domain;

import generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter@Getter@ObjectProp("采购订单")
public class OrderBill extends BaseDomain{
    public static final Integer NORMAL = 0;
    public static final Integer DONE = 1;
    @ObjectProp("订单编号")
    private String sn;
    @ObjectProp("业务时间")
    private Date vdate;
    @ObjectProp("审核状态")
    private Integer status;
    @ObjectProp("采购总金额")
    private BigDecimal totalAmount;
    @ObjectProp("采购总数量")
    private BigDecimal totalNumber;
    @ObjectProp("审核时间")
    private Date auditTime;
    @ObjectProp("录入时间")
    private Date inputTime;
    @ObjectProp("录入人")

    //对于关联关系,代码生成器生成后修改mapper.xml文件
    private Employee inputUser;
    @ObjectProp("审核人")
    private Employee auditor;
    @ObjectProp("供应商")
    private Supplier supplier;
    //订单与订单明细是多对一的关系,集合保存在one方
    private List<OrderBillItem> items = new ArrayList<>();
}
