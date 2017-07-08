package com.xmg.wms.service.impl;

import com.xmg.wms.VO.SaleChartVO;
import com.xmg.wms.mapper.SaleChartMapper;
import com.xmg.wms.query.SaleChartQueryObject;
import com.xmg.wms.service.ISaleChartService;
import lombok.Setter;

import java.util.List;

public class SaleChartServiceImpl implements ISaleChartService {
    @Setter
    private SaleChartMapper saleChartMapper;
    @Override
    public List<SaleChartVO> querySaleChart(SaleChartQueryObject qo) {
        return saleChartMapper.querySaleChart(qo);
    }
}
