package com.cfh.practice.product.service.impl;

import com.cfh.practice.product.dataobject.ProductInfo;
import com.cfh.practice.product.enums.ProductStatusEnum;
import com.cfh.practice.product.repository.ProductInfoRepository;
import com.cfh.practice.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: cfh
 * @Date: 2018/9/20 17:20
 * @Description:
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }
}
