package com.cfh.practice.product.config;

import com.cfh.practice.product.executor.ProductStockExecutor;
import com.cfh.practice.product.task.PublishStockTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: cfh
 * @Date: 2018/9/22 21:54
 * @Description: 在项目初始化时将所有在架商品的库存发布到redis库中
 */
//@Configuration
@Slf4j
public class ProductStockConfig {

    @Bean
    public ProductStockExecutor productStockExecutor(){
        ExecutorService executor = Executors.newSingleThreadExecutor();

        return new ProductStockExecutor(executor, new PublishStockTask());
    }
}
