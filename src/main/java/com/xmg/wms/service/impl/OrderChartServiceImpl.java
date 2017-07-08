package com.xmg.wms.service.impl;

import com.xmg.wms.VO.OrderChartVO;
import com.xmg.wms.mapper.OrderChartMapper;
import com.xmg.wms.query.OrderChartQueryObject;
import com.xmg.wms.service.IOrderChartService;
import lombok.Setter;

import java.util.List;

public class OrderChartServiceImpl implements IOrderChartService {
    @Setter
    private OrderChartMapper orderChartMapper;
    @Override
    public List<OrderChartVO> queryOrderChart(OrderChartQueryObject qo) {
        return orderChartMapper.queryOrderChart(qo);
    }
}
