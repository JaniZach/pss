package com.xmg.wms.web.action;

import com.alibaba.fastjson.JSON;
import com.xmg.wms.VO.SaleChartVO;
import com.xmg.wms.query.OrderChartQueryObject;
import com.xmg.wms.query.SaleChartQueryObject;
import com.xmg.wms.service.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChartAction extends BaseAction {
    @Setter
    private IOrderChartService orderChartService;
    @Setter
    private ISaleChartService saleChartService;
    @Setter
    private ISupplierService supplierService;
    @Setter
    private IBrandService brandService;
    @Setter
    private IClientService clientService;
    @Getter//订单报表使用的查询qo
    private OrderChartQueryObject orderQo = new OrderChartQueryObject();
    @Getter//销售报表使用的查询qo
    private SaleChartQueryObject saleQo = new SaleChartQueryObject();
    @Override
    public String execute() throws Exception {
        return super.execute();
    }
    public String orderChart() throws Exception {
        try{
            //将页面需要的供应商,品牌共享到页面
            putContext("suppliers",supplierService.list());
            putContext("brands",brandService.list());
            //将qo中的
            putContext("groupTypes",OrderChartQueryObject.groupTypes);
            putContext("list",orderChartService.queryOrderChart(orderQo));
        }catch (Exception e){
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return "orderChart";
    }
    public String saleChart() throws Exception {
        try{
            //将页面需要的客户,品牌共享到页面
            putContext("clients",clientService.list());
            putContext("brands",brandService.list());
            //将分组条件共享到页面
            putContext("groupTypes",SaleChartQueryObject.groupTypes);
            putContext("list",saleChartService.querySaleChart(saleQo));
        }catch (Exception e){
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return "saleChart";
    }
    public String saleChartByLine() throws Exception{
        //页面需要分组类型groupTypeName
        putContext("groupTypeName",SaleChartQueryObject.groupTypes.get(saleQo.getGroupType()));
        //页面需要的X轴,分组groutType
        List<String> listX = new ArrayList<>();
        List<BigDecimal> listY = new ArrayList<>();
        List<SaleChartVO> vos = saleChartService.querySaleChart(saleQo);
        for (SaleChartVO vo : vos) {
            listX.add(vo.getGroupType());
            listY.add(vo.getTotalAmount());
        }
        putContext("listX", JSON.toJSONString(listX));
        putContext("listY", JSON.toJSONString(listY));
        return "saleChartByLine";
    }
    public String saleChartByPie() throws Exception{
        //页面需要分组类型groupTypeName
        putContext("groupTypeName",SaleChartQueryObject.groupTypes.get(saleQo.getGroupType()));
        //页面需要的X轴,分组groutType
        List<Object[]> listPie = new ArrayList<>();
        List<SaleChartVO> vos = saleChartService.querySaleChart(saleQo);
        for (SaleChartVO vo : vos) {
            Object[] arr = {vo.getGroupType(), vo.getTotalAmount()};
            listPie.add(arr);
        }
        putContext("listPie",JSON.toJSONString(listPie));
        return "saleChartByPie";
    }
    public String saleChartByColumn() throws Exception{
        //页面需要分组类型groupTypeName
        putContext("groupTypeName",SaleChartQueryObject.groupTypes.get(saleQo.getGroupType()));
        //页面需要的X轴,分组groutType
        List<String> listX = new ArrayList<>();
        List<BigDecimal> listY = new ArrayList<>();
        List<SaleChartVO> vos = saleChartService.querySaleChart(saleQo);
        for (SaleChartVO vo : vos) {
            listX.add(vo.getGroupType());
            listY.add(vo.getTotalAmount());
        }
        putContext("listX", JSON.toJSONString(listX));
        putContext("listY", JSON.toJSONString(listY));
        return "saleChartByColumn";
    }
}
