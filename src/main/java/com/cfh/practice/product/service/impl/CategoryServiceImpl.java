package com.cfh.practice.product.service.impl;

import com.cfh.practice.product.dataobject.ProductCategory;
import com.cfh.practice.product.repository.ProductCategoryRepository;
import com.cfh.practice.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: cfh
 * @Date: 2018/9/20 17:19
 * @Description:
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
    }
}
