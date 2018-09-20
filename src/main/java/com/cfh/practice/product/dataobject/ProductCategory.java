package com.cfh.practice.product.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author: cfh
 * @Date: 2018/9/20 17:05
 * @Description:
 */
@Data
@Entity
public class ProductCategory {
    @Id//指定这个是表中的主键
    @GeneratedValue//指定该主键是自增的
    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;
}
