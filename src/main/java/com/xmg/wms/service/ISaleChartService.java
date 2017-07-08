package com.xmg.wms.service;

import com.xmg.wms.VO.SaleChartVO;
import com.xmg.wms.query.SaleChartQueryObject;

import java.util.List;

public interface ISaleChartService {
	List<SaleChartVO> querySaleChart(SaleChartQueryObject qo);
}
