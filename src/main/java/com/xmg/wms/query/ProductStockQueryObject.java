package com.xmg.wms.query;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter@Getter
public class ProductStockQueryObject extends QueryObject {
    //即时报表的高级查询
    private String keyword;//货品名称/编码
    private Long depotId = -1L;//仓库id
    private Long brandId = -1L;//品牌id
    private BigDecimal limitNum;//阈值(库存最高值)

    //设置分页大小
    @Override
    public Integer getPageSize() {
        return 10;
    }
}
