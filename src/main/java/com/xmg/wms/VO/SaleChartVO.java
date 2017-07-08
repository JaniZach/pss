package com.xmg.wms.VO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

//销售报表
@Setter@Getter
public class SaleChartVO {
    private String groupType;//分组类型
    private BigDecimal totalNumber;//销售总数量
    private BigDecimal totalAmount;//销售总价值
    private BigDecimal grossProfit;//毛利润
}
