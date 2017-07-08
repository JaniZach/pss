package com.xmg.wms.query;

import com.xmg.wms.util.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class OrderBillQueryObject extends QueryObject {
    //日期高级查询的处理
    @Setter
    private Date beginDate;
    @Setter
    private Date endDate;
    @Setter@Getter
    private Long supplierId = -1L;
    @Setter@Getter
    private int status = -1;

    //复写beginDate和endDate的get方法
    public Date getBeginDate() {
        return DateUtil.getBeginDate(beginDate);
    }

    public Date getEndDate() {
        return DateUtil.getEndDate(endDate);
    }
}
