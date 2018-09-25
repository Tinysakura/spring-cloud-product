package com.cfh.practice.order.config;

import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @Author: cfh
 * @Date: 2018/9/25 18:09
 * @Description:
 */
@Configuration
public class FeignHystrixConfig {
    @Bean
    @Scope("prototype")
    public Feign.Builder feignHystrixBuilder() {
        return Feign.builder();
    }
}
