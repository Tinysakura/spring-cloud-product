package com.cfh.practice.client;

import com.cfh.practice.common.DecreaseStockInput;
import com.cfh.practice.common.ProductInfoOutput;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: cfh
 * @Date: 2018/9/25 17:10
 * @Description:
 */
@Component
public class ProductClientFallback implements ProductClient{
    @Override
    public List<ProductInfoOutput> listForOrder(List<String> productIdList) {
        ProductInfoOutput productInfoOutput = new ProductInfoOutput();
        productInfoOutput.setProductName("trash");

        List<ProductInfoOutput> list = new ArrayList<>();
        list.add(productInfoOutput);

        return list;
    }

    @Override
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {

    }
}
