package com.cfh.practice.product.service;

import com.cfh.practice.product.dataobject.ProductInfo;

import java.util.List;

/**
 * @Author: cfh
 * @Date: 2018/9/20 17:18
 * @Description:
 */
public interface ProductService {
    /**
     * 查询所有在架商品列表
     */
    List<ProductInfo> findUpAll();
}
