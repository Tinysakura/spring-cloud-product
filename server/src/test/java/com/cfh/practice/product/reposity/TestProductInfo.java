package com.cfh.practice.product.reposity;

import com.cfh.practice.product.dto.CartDTO;
import com.cfh.practice.product.service.impl.ProductServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: cfh
 * @Date: 2018/9/20 23:15
 * @Description: 测试ProductInfoService
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestProductInfo {
    @Autowired
    ProductServiceImpl productService;

    @Test
    public void TestaFindList(){
        List<String> list = new ArrayList<>();

        list.add("157875196366160022");
        list.add("157875227953464068");

        Assert.assertTrue(productService.findList(list).size() > 0);
    }

    @Test
    public void increaseStock(){
        CartDTO cartDTO = new CartDTO();
        cartDTO.setProductId("157875196366160022");
        cartDTO.setProductQuantity(2);

        List<CartDTO> cartDTOS = new ArrayList<>();
        cartDTOS.add(cartDTO);

        productService.decreaseStock(cartDTOS);
    }
}
