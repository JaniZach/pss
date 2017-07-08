package com.xmg.wms.VO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

//订单报表
@Setter@Getter
public class OrderChartVO {
    private String groupType;//分组类型
    private BigDecimal totalNumber;//总数量
    private BigDecimal totalAmount;//总价值
}
