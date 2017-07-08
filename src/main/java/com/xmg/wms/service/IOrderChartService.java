package com.xmg.wms.service;

import com.xmg.wms.VO.OrderChartVO;
import com.xmg.wms.query.OrderChartQueryObject;

import java.util.List;

public interface IOrderChartService {
	List<OrderChartVO> queryOrderChart(OrderChartQueryObject qo);
}
