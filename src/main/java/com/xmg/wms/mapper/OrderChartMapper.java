package com.xmg.wms.mapper;

import com.xmg.wms.VO.OrderChartVO;
import com.xmg.wms.query.OrderChartQueryObject;

import java.util.List;

public interface OrderChartMapper {
    List<OrderChartVO> queryOrderChart(OrderChartQueryObject qo);
}