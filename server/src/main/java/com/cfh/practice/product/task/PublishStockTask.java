package com.cfh.practice.product.task;

import com.cfh.practice.product.dataobject.ProductInfo;
import com.cfh.practice.product.enums.ProductStatusEnum;
import com.cfh.practice.product.repository.ProductInfoRepository;
import com.sun.javafx.binding.StringFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

/**
 * @Author: cfh
 * @Date: 2018/9/22 21:58
 * @Description: 将数据库当前所有商品的库存更新到Redis中
 */
@Slf4j
public class PublishStockTask implements Runnable{

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
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
