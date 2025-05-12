package com.cloud.junjie.product.bean;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {

    /**
     * 商品id
     */
    private Long id;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品数量
     */
    private int number;

}
