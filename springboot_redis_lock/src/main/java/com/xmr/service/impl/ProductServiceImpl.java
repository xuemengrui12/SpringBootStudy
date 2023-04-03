package com.xmr.service.impl;

import com.xmr.RedisLock;
import com.xmr.entity.Product;
import com.xmr.dao.ProductMapper;
import com.xmr.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * (Product)表服务实现类
 *
 * @author makejava
 * @since 2020-03-06 17:45:19
 */
@Service("productService")
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private RedisLock redisLock;

    @Override
    public boolean decrementProductStore(Long productId, Integer productQuantity) {
        String key = "product_lock_" + productId;
        long time = System.currentTimeMillis();
        try {
            //如果加锁失败
            if (!redisLock.tryLock(key, time,1L,TimeUnit.SECONDS,2)) {
                return false;
            }
            Product productInfo = productMapper.queryById(productId);
            //如果库存为空
            if (productInfo.getCount() == 0) {
                return false;
            }
            //减库存操作
            productInfo.setCount(productInfo.getCount() - productQuantity);
            productMapper.update(productInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            //解锁
            redisLock.unlock(key, String.valueOf(time));
        }
        return true;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Product queryById(Long id) {
        return this.productMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Product> queryAllByLimit(int offset, int limit) {
        return this.productMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    @Override
    public Product insert(Product product) {
        this.productMapper.insert(product);
        return product;
    }

    /**
     * 修改数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    @Override
    public Product update(Product product) {
        this.productMapper.update(product);
        return this.queryById(product.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.productMapper.deleteById(id) > 0;
    }
}