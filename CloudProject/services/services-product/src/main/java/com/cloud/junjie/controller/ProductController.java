package com.cloud.junjie.controller;

import com.cloud.junjie.product.bean.Product;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Random;

@RestController
@RequestMapping("product")
public class ProductController {

    @GetMapping("/getProduct")
    public Product getProduct(@PathParam("productId") Long productId){
        System.out.println("holle");
        Product product = new Product();
        product.setId(productId);
        product.setPrice(new BigDecimal(new Random().nextInt(productId.intValue())));
        return product;
    }

}
