package com.cfh.practice.product.service;

import com.cfh.practice.product.dataobject.ProductCategory;

import java.util.List;

/**
 * @Author: cfh
 * @Date: 2018/9/20 17:19
 * @Description:
 */
public interface CategoryService {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
