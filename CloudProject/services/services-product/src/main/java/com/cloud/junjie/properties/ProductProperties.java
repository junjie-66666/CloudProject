package com.cloud.junjie.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "product")
public class ProductProperties {

    String name;

    String price;

}
