package com.cfh.practice.product.enums;

import lombok.Getter;

/**
 * @Author: cfh
 * @Date: 2018/9/20 16:52
 * @Description: 商品上下架状态
 */
@Getter
public enum ProductStatusEnum {
    UP(1, "在架"),
    DOWN(2, "下架"),
    ;

    private Integer code;
    private String  message;

    private ProductStatusEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
