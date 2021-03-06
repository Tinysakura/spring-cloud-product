package com.cfh.practice.common;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品详情出参，由于要同时被server与client模块使用所以放在common module中
 */
@Data
public class ProductInfoOutput {

    private String productId;

    /** 名字. */
    private String productName;

    /** 单价. */
    private BigDecimal productPrice;

    /** 库存. */
    private Integer productStock;

    /** 描述. */
    private String productDescription;

    /** 小图. */
    private String productIcon;

    /** 状态, 0正常1下架. */
    private Integer productStatus;

    /** 类目编号. */
    private Integer categoryType;
}
