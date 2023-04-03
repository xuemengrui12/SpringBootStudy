package com.xmr.controller;

import com.xmr.annotation.CacheLock;
import com.xmr.annotation.LockedObject;
import com.xmr.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (Product)表控制层
 *
 * @author makejava
 * @since 2020-03-06 17:45:20
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    /**
     * 服务对象
     */
    @Autowired
    private IProductService productService;

//    /**
//     * 通过主键查询单条数据
//     *
//     * @param id 主键
//     * @return 单条数据
//     */
//    @GetMapping("selectOne")
//    public Product selectOne(Long id) {
//        return this.productService.queryById(id);
//    }


    @GetMapping("/test")
    @CacheLock(lockedPrefix = "product_lock_",timeOut = 2,expireTime = 2)
    public String decrementTest(@LockedObject  Long productId) {
        for (int i = 0; i <10 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    productService.decrementProductStore(1L, 1);
                }
            }).start();
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        return "success";
    }

}