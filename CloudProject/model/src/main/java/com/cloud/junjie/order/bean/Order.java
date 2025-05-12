package com.cloud.junjie.order.bean;

import com.cloud.junjie.product.bean.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class Order {

    /**
     * 订单id
     */
    private Long id;

    /**
     * 订单总金额
     */
    private BigDecimal orderTotalAmount;

    /**
     * 订单优惠金额
     */
    private BigDecimal orderDiscountAmount;

    /**
     * 订单实付金额
     */
    private BigDecimal orderPayAmount;

    /**
     * 订单创建时间
     */
    private Date createTime;

    /**
     * 订单支付时间
     */
    private Date payTime;

    /**
     * 商品信息
     */
    private List<Product> productList;

}
