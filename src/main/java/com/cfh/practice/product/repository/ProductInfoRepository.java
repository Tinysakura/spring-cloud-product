package com.cfh.practice.product.repository;

import com.cfh.practice.product.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: cfh
 * @Date: 2018/9/20 17:08
 * @Description:
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    /**
     * 根据商品的上下架状态查询商品列表
     * @param status
     * @return
     */
    List<ProductInfo> findByProductStatus(Integer status);

    List<ProductInfo> findByProductIdIn(List<Integer> ids);
}
