package com.cloud.junjie.controller;

import com.cloud.junjie.order.bean.Order;
import com.cloud.junjie.order.dto.CreateOrderDto;
import com.cloud.junjie.product.bean.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @PostMapping("/createOrder")
    public Order createOrder(@RequestBody CreateOrderDto createOrderDto){
        Order order = new Order();
        List<Long> productIds = createOrderDto.getProductIds();
        BigDecimal totalPrice = BigDecimal.ZERO;
        List<Product> productList = new ArrayList<>();
        for (Long productId : productIds) {
            Product product = getProductByAnnotation(productId);
            totalPrice = totalPrice.add(product != null ? product.getPrice() : BigDecimal.ZERO);
            productList.add(product);
        }
        order.setOrderTotalAmount(totalPrice);
        order.setProductList(productList);
        return order;
    }

    /**
     * 根据商品id获取商品信息
     * @param productId 商品id
     * @return
     */
    private Product getProductById(Long productId) {
        List<ServiceInstance> instances = discoveryClient.getInstances("services-product");
        if (!instances.isEmpty()) {
            ServiceInstance serviceInstance = instances.get(0);
            // 远程连接的URL
            String url = "http://"
                    + serviceInstance.getHost() + ":"
                    + serviceInstance.getPort()
                    + "/product/getProduct?productId=" + productId;
            return restTemplate.getForObject(url, Product.class);
        }
        return null;
    }

    /**
     * 负载均衡的方式根据商品id获取商品信息
     * @param productId 商品id
     * @return
     */
    private Product getProductByLoadBalancer(Long productId) {
        // 默认为轮询的方式
        ServiceInstance serviceInstance = loadBalancerClient.choose("services-product");
        if (!StringUtils.isEmpty(serviceInstance)) {
            // 远程连接的URL
            String url = "http://"
                    + serviceInstance.getHost() + ":"
                    + serviceInstance.getPort()
                    + "/product/getProduct?productId=" + productId;
            return restTemplate.getForObject(url, Product.class);
        }
        return null;
    }

    /**
     * 通过注解的方式负载均衡的根据商品id获取商品信息
     * @param productId 商品id
     * @return
     */
    private Product getProductByAnnotation(Long productId) {
        // 远程连接的URL
        String url = "http://services-product/product/getProduct?productId=" + productId;
        log.info("url:{}", url);
        return restTemplate.getForObject(url, Product.class);
    }

}
