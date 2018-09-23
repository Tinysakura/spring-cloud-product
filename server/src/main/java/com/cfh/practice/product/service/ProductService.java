package com.cfh.practice.product.service;

import com.cfh.practice.common.DecreaseStockInput;
import com.cfh.practice.common.ProductInfoOutput;
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

    /**
     * 查询商品列表
     * @param productIdList
     * @return
     */
    List<ProductInfoOutput> findList(List<String> productIdList);

    /**
     * 根据购物车信息扣库存
     * @param cartDTOList
     */
    void decreaseStock(List<DecreaseStockInput> cartDTOList);
}
