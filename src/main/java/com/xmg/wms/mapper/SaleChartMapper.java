package com.xmg.wms.mapper;

import com.xmg.wms.VO.SaleChartVO;
import com.xmg.wms.domain.SaleAccount;
import com.xmg.wms.query.SaleChartQueryObject;

import java.util.List;

public interface SaleChartMapper{
    List<SaleChartVO> querySaleChart(SaleChartQueryObject qo);
}