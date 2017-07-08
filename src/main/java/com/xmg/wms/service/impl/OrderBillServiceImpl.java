package com.xmg.wms.service.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.sun.tools.internal.xjc.reader.dtd.bindinfo.BIAttribute;
import com.xmg.wms.domain.Employee;
import com.xmg.wms.domain.OrderBillItem;
import com.xmg.wms.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.wms.domain.OrderBill;
import com.xmg.wms.mapper.OrderBillMapper;
import com.xmg.wms.page.PageResult;
import com.xmg.wms.query.OrderBillQueryObject;
import com.xmg.wms.service.IOrderBillService;
import lombok.Setter;

public class OrderBillServiceImpl implements IOrderBillService {
    @Setter
    private OrderBillMapper orderBillMapper;

    public void delete(Long id) {
        //删除订单,应先删除订单关联的列表清单,再删除订单(外键关联)
        orderBillMapper.deleteItem(id);
        orderBillMapper.delete(id);
    }

    public void save(OrderBill bill) {
        //保存录入人
        bill.setInputUser(UserContext.getCurrentUser());
        //保存录入时间
        bill.setInputTime(new Date());
        //保存录入状态
        bill.setStatus(OrderBill.NORMAL);
        //计算采购总金额,总数量
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal totalNumber = BigDecimal.ZERO;
        List<OrderBillItem> items = bill.getItems();
        for (OrderBillItem item : items) {
            item.setBill(bill);//设置关联关系
            BigDecimal costPrice = item.getCostPrice();
            BigDecimal number = item.getNumber();
            BigDecimal amount = costPrice.multiply(number).setScale(2,BigDecimal.ROUND_HALF_UP);
            //页面并没有将 amount穿过来,因此,要对item设值
            item.setAmount(amount);
            totalAmount = totalAmount.add(amount);
            totalNumber = totalNumber.add(number);
        }
        bill.setTotalAmount(totalAmount);
        bill.setTotalNumber(totalNumber);
        //先保存订单,得到订单id,再保存清单
        orderBillMapper.save(bill);
        for (OrderBillItem item : items) {
            //保存订单后,自动获取主键,此时再保存列表清单
            orderBillMapper.saveItem(item);
        }
    }

    public OrderBill get(Long id) {
        return orderBillMapper.get(id);
    }

    public List<OrderBill> list() {
        return orderBillMapper.list();
    }

    public void update(OrderBill bill) {
        //更新操作,应先删除原来的列表清单,再保存最新的列表清单
        //删除原来的列表清单
        orderBillMapper.deleteItem(bill.getId());
        //重新保存列表清单
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal totalNumber = BigDecimal.ZERO;
        for (OrderBillItem item : bill.getItems()) {
            BigDecimal costPrice = item.getCostPrice();
            BigDecimal number = item.getNumber();
            BigDecimal amount = costPrice.multiply(number).setScale(BigDecimal.ROUND_HALF_UP);
            item.setAmount(amount);
            //设置关联关系
            item.setBill(bill);
            //修改操作,bill本就有了id
            orderBillMapper.saveItem(item);
            totalAmount = totalAmount.add(amount);
            totalNumber = totalNumber.add(number);
        }
        //设置总金额,总数量
        bill.setTotalAmount(totalAmount);
        bill.setTotalNumber(totalNumber);
        //更新操作(状态等信息不需要更新)
        orderBillMapper.update(bill);
    }

    @Override
    public PageResult pageQuery(OrderBillQueryObject qo) {
        Long count = orderBillMapper.queryCount(qo);
        if (count <= 0) {
            return new PageResult(Collections.EMPTY_LIST, 0, 1, qo.getPageSize());
        }
        List<OrderBill> result = orderBillMapper.queryList(qo);
        PageResult pageResult = new PageResult(result, count.intValue(), qo.getCurrentPage(), qo.getPageSize());
        return pageResult;
    }

    @Override
    public void batchDelete(List<Long> ids) {
        //此处应注意外键约束的时候,应先删除关系表中相关数据
        //删除关系
        for (Long id : ids) {
            //删除订单,应先删除订单关联的列表清单,再删除订单(外键关联)
            orderBillMapper.deleteItem(id);
        }
        //删除关系
        orderBillMapper.batchDelete(ids);
    }

    @Override
    public void audit(OrderBill orderBill) {
        //审核操作,需要保存 审核状态,审核人,审核时间
        if(orderBill.getStatus()==0){
            //审核状态修改
            orderBill.setStatus(OrderBill.DONE);
            //审核人保存
            orderBill.setAuditor(UserContext.getCurrentUser());
            //审核时间保存
            orderBill.setAuditTime(new Date());
            orderBillMapper.audit(orderBill);
        }else{
            throw new RuntimeException("该订单已审核!");
        }
    }
}
