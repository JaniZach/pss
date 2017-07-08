package com.xmg.wms.domain;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Setter@Getter@ObjectProp("商品管理")
public class Product extends BaseDomain{
    @ObjectProp("货品名称")
    private String name;
    @ObjectProp("货品编码")
    private String sn;
    @ObjectProp("成本价格")
    private BigDecimal costPrice;
    @ObjectProp("销售价格")
    private BigDecimal salePrice;
    @ObjectProp("货品图片")
    private String imagePath;
    @ObjectProp("备注")
    private String intro;
    @ObjectProp("货品品牌")
    private Brand brand;
    public String getSmallImagePath(){
        if(!StringUtils.isEmpty(imagePath)){
            int i = imagePath.lastIndexOf(".");
            return imagePath.substring(0,i)+"_small"+imagePath.substring(i);
        }
        return null;
    }
    //获取productJson对象
    public String getProductJson(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",getId());
        map.put("name",getName());
        map.put("brandName",getBrand()==null?"":getBrand().getName());//避免空指针异常的操作
        map.put("costPrice",getCostPrice());
        map.put("salePrice",getSalePrice());
        return JSON.toJSONString(map);
    }
}
