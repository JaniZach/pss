package com.xmg.wms.service.impl;

import com.xmg.wms.domain.*;
import com.xmg.wms.mapper.ProductStockMapper;
import com.xmg.wms.mapper.StockIncomeBillMapper;
import com.xmg.wms.page.PageResult;
import com.xmg.wms.query.StockIncomeBillQueryObject;
import com.xmg.wms.service.IProductStockService;
import com.xmg.wms.service.IStockIncomeBillService;
import com.xmg.wms.util.UserContext;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class StockIncomeBillServiceImpl implements IStockIncomeBillService {
    @Setter
    private StockIncomeBillMapper stockIncomeBillMapper;
    @Setter
    private IProductStockService productStockService;

    public void delete(Long id) {
        //删除入库单,应先删除相关清单
        stockIncomeBillMapper.deleteItem(id);
        stockIncomeBillMapper.delete(id);
    }

    public void save(StockIncomeBill bill) {
        //保存录入人,录入时间
        bill.setInputUser(UserContext.getCurrentUser());
        bill.setInputTime(new Date());
        //应在审核后,才能进行入库,才会在库存数据库中存在总金额,总数量
            //设置总金额,总数量
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal totalNumber = BigDecimal.ZERO;
        for (StockIncomeBillItem item : bill.getItems()) {
            BigDecimal costPrice = item.getCostPrice();
            BigDecimal number = item.getNumber();
            BigDecimal amount = costPrice.multiply(number);
            item.setAmount(amount);
            //设置入库单与清单的关联关系
            item.setBill(bill);
            totalAmount = totalAmount.add(amount);
            totalNumber = totalNumber.add(number);
        }
        bill.setTotalAmount(totalAmount);
        bill.setTotalNumber(totalNumber);
        stockIncomeBillMapper.save(bill);
        for (StockIncomeBillItem item : bill.getItems()) {
            stockIncomeBillMapper.saveItem(item);
        }
    }

    public StockIncomeBill get(Long id) {
        return stockIncomeBillMapper.get(id);
    }

    public List<StockIncomeBill> list() {
        return stockIncomeBillMapper.list();
    }

    public void update(StockIncomeBill bill) {
        //更新操作,先删除原来的列表清单,再重新保存列表清单
        stockIncomeBillMapper.deleteItem(bill.getId());
        //审核通过后,货品才可以入库,才会在库存数据库中有总金额,总数量
            //设置总金额,总数量
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal totalNumber = BigDecimal.ZERO;
        for (StockIncomeBillItem item : bill.getItems()) {
            BigDecimal costPrice = item.getCostPrice();
            BigDecimal number = item.getNumber();
            BigDecimal amount = costPrice.multiply(number);
            item.setAmount(amount);
            //设置入库单与清单的关联关系
            item.setBill(bill);
            totalAmount = totalAmount.add(amount);
            totalNumber = totalNumber.add(number);
        }
        bill.setTotalAmount(totalAmount);
        bill.setTotalNumber(totalNumber);
        stockIncomeBillMapper.update(bill);
        //保存入库单后,再保存清单
        for (StockIncomeBillItem item : bill.getItems()) {
            stockIncomeBillMapper.saveItem(item);
        }
    }

    @Override
    public PageResult pageQuery(StockIncomeBillQueryObject qo) {
        Long count = stockIncomeBillMapper.queryCount(qo);
        if (count <= 0) {
            return new PageResult(Collections.EMPTY_LIST, 0, 1, qo.getPageSize());
        }
        List<StockIncomeBill> result = stockIncomeBillMapper.queryList(qo);
        PageResult pageResult = new PageResult(result, count.intValue(), qo.getCurrentPage(), qo.getPageSize());
        return pageResult;
    }

    @Override
    public void batchDelete(List<Long> ids) {
        //此处应注意外键约束的时候,应先删除关系表中相关数据
        //删除关系
        for (Long id : ids) {
            //删除入库单,应先删除相关清单
            stockIncomeBillMapper.deleteItem(id);
        }
        //删除关系
        stockIncomeBillMapper.batchDelete(ids);
    }

    @Override
    public void audit(StockIncomeBill bill) {
        if (bill.getStatus() == 0) {
            //1,审核操作--设置审核人,审核状态,审核时间
            bill.setAuditor(UserContext.getCurrentUser());
            bill.setStatus(StockIncomeBill.DONE);
            bill.setAuditTime(new Date());
            stockIncomeBillMapper.audit(bill);
            //2,审核完成,应将相关信息存储到库存表中
                //获取仓库信息
            Depot depot = bill.getDepot();
            List<StockIncomeBillItem> items = bill.getItems();
            for (StockIncomeBillItem item : items) {
                //入库操作
                productStockService.income(depot,item.getProduct(),item.getNumber(),item.getCostPrice());
            }
        } else {
            throw new RuntimeException("该入库单已审核!");
        }
    }
}
