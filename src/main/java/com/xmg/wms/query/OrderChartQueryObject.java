package com.xmg.wms.query;

import com.xmg.wms.util.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

//订单报表高级查询条件
@Setter
public class OrderChartQueryObject extends QueryObject {
    public static Map<String,String> groupTypes = new LinkedHashMap<>();
    static {
        groupTypes.put("e.name","订货人员");
        groupTypes.put("p.name","货品名称");
        groupTypes.put("s.name","供应商");
        groupTypes.put("b.name","货品品牌");
        groupTypes.put("DATE_FORMAT(bill.vdate,'%Y-%m')","订货日期(月)");
        groupTypes.put("DATE_FORMAT(bill.vdate,'%Y-%m-%d')","订货日期(日)");
    }
    private Date beginDate;
    private Date endDate;
    @Getter
    private String keyword;//货品名
    @Getter
    private Long supplierId = -1L;//供应商
    @Getter
    private Long brandId = -1L;//品牌
    @Getter
    private String groupType = "e.name";//设置默认值

    public Date getBeginDate() {
        return DateUtil.getBeginDate(beginDate);
    }

    public Date getEndDate() {
        return DateUtil.getEndDate(endDate);
    }
}
