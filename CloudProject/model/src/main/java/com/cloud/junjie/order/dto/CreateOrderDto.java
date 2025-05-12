package com.cloud.junjie.order.dto;

import com.cloud.junjie.coupon.bean.Coupon;
import com.cloud.junjie.product.bean.Product;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderDto {

    private Long userId;

    private List<Long> productIds;

    private List<Coupon> couponList;

}
