package com.cfh.practice.product.service.impl;

import com.cfh.practice.product.dataobject.ProductInfo;
import com.cfh.practice.product.dto.CartDTO;
import com.cfh.practice.product.enums.ProductStatusEnum;
import com.cfh.practice.product.enums.ResultEnum;
import com.cfh.practice.product.exception.ProductException;
import com.cfh.practice.product.repository.ProductInfoRepository;
import com.cfh.practice.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<ProductInfo> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList);
    }

    @Transactional
    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList){
            Optional<ProductInfo> cartOption = productInfoRepository.findById(cartDTO.getProductId());

            //判断是否存在该商品
            if (!cartOption.isPresent()){
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            ProductInfo productInfo = cartOption.get();

            //判断库存是否足够
            int residueStock = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (residueStock < 0){
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(residueStock);
            productInfoRepository.save(productInfo);
        }
    }
}
