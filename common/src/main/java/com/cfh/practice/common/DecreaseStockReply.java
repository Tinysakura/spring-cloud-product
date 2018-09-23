package com.cfh.practice.common;

import lombok.Data;

/**
 * @Author: cfh
 * @Date: 2018/9/22 21:50
 * @Description: 关于异步减库存结果的消息类
 */
@Data
public class DecreaseStockReply {

    String orderId;

    int status;//0表示成功，1表示失败
}
