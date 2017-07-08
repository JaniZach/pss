package com.xmg.wms.mapper;

import com.xmg.wms.domain.SaleAccount;

public interface SaleAccountMapper{
    //销售帐只需要提供save方法
    void save(SaleAccount saleAccount);
}