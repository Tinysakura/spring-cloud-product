package com.cfh.practice.product.executor;

import java.util.concurrent.ExecutorService;

/**
 * @Author: cfh
 * @Date: 2018/9/22 22:06
 * @Description:
 */
public class ProductStockExecutor {
    ExecutorService threadPoolExecutor;

    public ProductStockExecutor(ExecutorService threadPoolExecutor, Runnable task){
        this.threadPoolExecutor = threadPoolExecutor;

        threadPoolExecutor.execute(task);
    }
}
