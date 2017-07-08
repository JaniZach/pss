package com.xmg.wms.domain;

import generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter@Getter@ObjectProp("销售出库单")
public class StockOutcomeBill extends BaseDomain {
    public static final Integer NORMAL = 0;
    public static final Integer DONE = 1;
    @ObjectProp("出库单编号")
    private String sn;
    @ObjectProp("业务时间")
    private Date vdate;
    @ObjectProp("审核状态")
    private int status;
    @ObjectProp("出库总金额")
    private BigDecimal totalAmount;
    @ObjectProp("出库总数量")
    private BigDecimal totalNumber;
    @ObjectProp("审核时间")
    private Date auditTime;
    @ObjectProp("录入时间")
    private Date inputTime;
    //关联关系
    @ObjectProp("录入人")
    private Employee inputUser;
    @ObjectProp("审核人")
    private Employee auditor;
    @ObjectProp("仓库")
    private Depot depot;
    @ObjectProp("客户")
    private Client client;
    //订单与清单的关系
    @ObjectProp("出库清单")
    private List<StockOutcomeBillItem> items = new ArrayList<>();
}
