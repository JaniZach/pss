package com.xmg.wms.service.impl;

import com.xmg.wms.domain.*;
import com.xmg.wms.mapper.ProductStockMapper;
import com.xmg.wms.mapper.StockOutcomeBillMapper;
import com.xmg.wms.page.PageResult;
import com.xmg.wms.query.StockOutcomeBillQueryObject;
import com.xmg.wms.service.IProductStockService;
import com.xmg.wms.service.ISaleAccountService;
import com.xmg.wms.service.IStockOutcomeBillService;
import com.xmg.wms.util.UserContext;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class StockOutcomeBillServiceImpl implements IStockOutcomeBillService {
    @Setter
    private StockOutcomeBillMapper stockOutcomeBillMapper;
    @Setter
    private IProductStockService productStockService;
    @Setter
    private ISaleAccountService saleAccountService;//保存 销售帐

    public void delete(Long id) {
        //删除入库单,应先删除相关清单
        stockOutcomeBillMapper.deleteItem(id);
        stockOutcomeBillMapper.delete(id);
    }

    public void save(StockOutcomeBill bill) {
        //保存录入人,录入时间
        bill.setInputUser(UserContext.getCurrentUser());
        bill.setInputTime(new Date());
            //设置总金额,总数量
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal totalNumber = BigDecimal.ZERO;
        for (StockOutcomeBillItem item : bill.getItems()) {
            BigDecimal costPrice = item.getSalePrice();
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
        stockOutcomeBillMapper.save(bill);
        for (StockOutcomeBillItem item : bill.getItems()) {
            stockOutcomeBillMapper.saveItem(item);
        }
    }

    public StockOutcomeBill get(Long id) {
        return stockOutcomeBillMapper.get(id);
    }

    public List<StockOutcomeBill> list() {
        return stockOutcomeBillMapper.list();
    }

    public void update(StockOutcomeBill bill) {
        //更新操作,先删除原来的出库单明细,再重新保存出库单明细
        stockOutcomeBillMapper.deleteItem(bill.getId());
            //设置总金额,总数量
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal totalNumber = BigDecimal.ZERO;
        for (StockOutcomeBillItem item : bill.getItems()) {
            BigDecimal costPrice = item.getSalePrice();
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
        stockOutcomeBillMapper.update(bill);
        //保存出库单后,再保存清单
        for (StockOutcomeBillItem item : bill.getItems()) {
            stockOutcomeBillMapper.saveItem(item);
        }
    }

    @Override
    public PageResult pageQuery(StockOutcomeBillQueryObject qo) {
        Long count = stockOutcomeBillMapper.queryCount(qo);
        if (count <= 0) {
            return new PageResult(Collections.EMPTY_LIST, 0, 1, qo.getPageSize());
        }
        List<StockOutcomeBill> result = stockOutcomeBillMapper.queryList(qo);
        PageResult pageResult = new PageResult(result, count.intValue(), qo.getCurrentPage(),
                qo.getPageSize());
        return pageResult;
    }

    @Override
    public void batchDelete(List<Long> ids) {
        //此处应注意外键约束的时候,应先删除关系表中相关数据
        //删除关系
        for (Long id : ids) {
            //删除出库单,应先删除相关清单
            stockOutcomeBillMapper.deleteItem(id);
        }
        //删除关系
        stockOutcomeBillMapper.batchDelete(ids);
    }

    @Override
    public void audit(StockOutcomeBill bill) {
        if (bill.getStatus() == 0) {
                //获取仓库信息
            Depot depot = bill.getDepot();
            List<StockOutcomeBillItem> items = bill.getItems();
            for (StockOutcomeBillItem item : items) {
               //进行出库操作,此处返回值是 销售帐 所需要的成本价
                BigDecimal costPrice = productStockService.outcome(depot, item.getProduct(),
                        item.getNumber());
                //进行审核操作
                //1,审核操作--设置审核人,审核状态,审核时间
                bill.setAuditor(UserContext.getCurrentUser());
                bill.setStatus(StockOutcomeBill.DONE);
                bill.setAuditTime(new Date());
                //2,审核完成,更新库存表中相关信息
                stockOutcomeBillMapper.audit(bill);
                //审核完成后,进行 销售帐 的save操作
                //每一笔出库单,都对应着一份销售帐记录
                SaleAccount saleAccount = new SaleAccount();//创建 销售账 对象
                saleAccount.setProduct(item.getProduct());//产品
                saleAccount.setVdate(bill.getVdate());//销售时间
                saleAccount.setClient(bill.getClient());//客户
                saleAccount.setNumber(item.getNumber());//销售数量
                saleAccount.setCostPrice(costPrice);//成本价格
                saleAccount.setCostAmount(costPrice.multiply(item.getNumber())//成本小计
                        .setScale(2,BigDecimal.ROUND_HALF_UP));
                saleAccount.setSalePrice(item.getSalePrice());//销售价格
                saleAccount.setSaleAmount(item.getAmount());//销售小计
                saleAccount.setSaleman(bill.getInputUser());//销售人员
                //执行 销售帐 的保存操作
                saleAccountService.save(saleAccount);
            }
        } else {
            throw new RuntimeException("该出库单已审核!");
        }
    }
}
