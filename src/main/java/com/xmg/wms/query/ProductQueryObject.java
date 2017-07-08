package com.xmg.wms.query;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class ProductQueryObject extends QueryObject {
    //商品的高级查询
    private String keyword;
    private Long brandId = -1L;
}
