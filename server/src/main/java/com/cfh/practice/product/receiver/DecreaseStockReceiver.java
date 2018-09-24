package com.cfh.practice.product.receiver;

import com.cfh.practice.common.DecreaseStockReply;
import com.cfh.practice.order.dataobject.OrderDetail;
import com.cfh.practice.order.dto.OrderDTO;
import com.cfh.practice.product.dataobject.ProductInfo;
import com.cfh.practice.product.repository.ProductInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: cfh
 * @Date: 2018/9/22 22:14
 * @Description: 接受并处理减库存消息
 */
@Slf4j
@Component
public class DecreaseStockReceiver {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @RabbitListener(queuesToDeclare = @Queue("decreaseStockQueue"))
    @SendTo("decreaseStockReplyQueue")
    public DecreaseStockReply handleDecreaseStockMessage(OrderDTO orderDTO){
        DecreaseStockReply decreaseStockReply = new DecreaseStockReply();
        decreaseStockReply.setOrderId(orderDTO.getOrderId());

        //尝试减数据库库存
        int isSuccess = decreaseStock(orderDTO.getOrderDetailList());
        decreaseStockReply.setStatus(isSuccess);

        return decreaseStockReply;
    }

    @Transactional
    public int decreaseStock(List<OrderDetail> orderDetails){
        Map<String, Integer> marginInfoMap = new HashMap<>();
        Map<String, ProductInfo> productInfoMap = new HashMap<>();

        //检查用户订单中的所有商品是否都有余量
        for (OrderDetail orderDetail : orderDetails) {
            ProductInfo productInfo = productInfoRepository.findById(orderDetail.getProductId()).get();

            int stock = productInfo.getProductStock();
            int margin = stock - orderDetail.getProductQuantity();

            if (margin < 0){
                return 1;
            }

            marginInfoMap.put(orderDetail.getProductId(), margin);
            productInfoMap.put(orderDetail.getProductId(), productInfo);
        }

        //如果都有库存则统一扣除然后返回扣除成功的消息
        for (Map.Entry entry : marginInfoMap.entrySet()) {
            ProductInfo productInfo = productInfoMap.get(entry.getKey());
            productInfo.setProductStock((int)entry.getValue());

            productInfoRepository.save(productInfo);
        }

        return 0;
    }
}
