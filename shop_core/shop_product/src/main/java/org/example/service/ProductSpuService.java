package org.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.ProductSpu;


/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author tangguo
 * @since 2025-10-07
 */
public interface ProductSpuService extends IService<ProductSpu> {

    void saveProductSpu(ProductSpu productSpu);
}
