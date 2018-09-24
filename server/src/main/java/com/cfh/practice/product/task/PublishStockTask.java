package com.cfh.practice.product.task;

import com.cfh.practice.product.dataobject.ProductInfo;
import com.cfh.practice.product.enums.ProductStatusEnum;
import com.cfh.practice.product.repository.ProductInfoRepository;
import com.sun.javafx.binding.StringFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: cfh
 * @Date: 2018/9/23 13:12
 * @Description: 定时更新数据库的实时库存到Redis中
 */
@Slf4j
@Component
public class PublishStockTask {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 每小时执行一次
     */
    @Scheduled(fixedRate = 3600000L)
    public void run() {
        log.info("更新库存到Redis中");

        List<ProductInfo> onSale = productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());

        String keyFormat = "product_stock_%s";
        for (ProductInfo productInfo : onSale){
            String key = StringFormatter.format(keyFormat, productInfo.getProductId()).getValue();

            redisTemplate.opsForValue().set(key, String.valueOf(productInfo.getProductStock()));
        }

        log.info("redis中的库存更新完成");
    }
}
