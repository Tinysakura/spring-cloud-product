package com.cfh.practice.product.service.impl;

import com.cfh.practice.common.DecreaseStockInput;
import com.cfh.practice.common.ProductInfoOutput;
import com.cfh.practice.product.dataobject.ProductInfo;
import com.cfh.practice.product.enums.ProductStatusEnum;
import com.cfh.practice.product.enums.ResultEnum;
import com.cfh.practice.product.exception.ProductException;
import com.cfh.practice.product.repository.ProductInfoRepository;
import com.cfh.practice.product.service.ProductService;
import com.cfh.practice.product.utils.JsonUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: cfh
 * @Date: 2018/9/20 17:20
 * @Description:
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductInfoRepository productInfoRepository;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfoOutput> findList(List<String> productIdList) {
        //使用lambda表达式转换dao层的查询结果为List<ProductInfoOutput>
        return productInfoRepository.findByProductIdIn(productIdList).stream()
                .map(e -> {
                    ProductInfoOutput output = new ProductInfoOutput();
                    BeanUtils.copyProperties(e, output);
                    return output;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputs) {

        List<ProductInfo> productInfoList = decreaseStockInDB(decreaseStockInputs);

        //使用lambda表达式将productInfoList转换为productInfoOutputList
        List<ProductInfoOutput> productInfoOutputList = productInfoList.stream().map(e -> {
            ProductInfoOutput output = new ProductInfoOutput();
            BeanUtils.copyProperties(e, output);
            return output;
        }).collect(Collectors.toList());

        String message = JsonUtil.obj2String(productInfoOutputList);
        //rabbitTemplate.convertAndSend("productInfoOutput", message);
    }

    @Transactional
    public List<ProductInfo> decreaseStockInDB(List<DecreaseStockInput> decreaseStockInputs){
        List<ProductInfo> productInfoList = new ArrayList<>();

        for (DecreaseStockInput decreaseStockInput : decreaseStockInputs){
            Optional<ProductInfo> cartOption = productInfoRepository.findById(decreaseStockInput.getProductId());

            //判断是否存在该商品
            if (!cartOption.isPresent()){
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            ProductInfo productInfo = cartOption.get();

            //判断库存是否足够
            int residueStock = productInfo.getProductStock() - decreaseStockInput.getProductQuantity();
            if (residueStock < 0){
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(residueStock);
            //productInfoRepository.save(productInfo);
            productInfoList.add(productInfo);
        }

        return productInfoList;
    }
}
