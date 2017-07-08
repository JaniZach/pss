package com.xmg.wms.service.impl;

import com.xmg.wms.domain.SaleAccount;
import com.xmg.wms.mapper.SaleAccountMapper;
import com.xmg.wms.service.ISaleAccountService;
import lombok.Setter;

public class SaleAccountServiceImpl implements ISaleAccountService {
    @Setter
    private SaleAccountMapper saleAccountMapper;
    @Override
    public void save(SaleAccount saleAccount) {
           saleAccountMapper.save(saleAccount);
    }
}
