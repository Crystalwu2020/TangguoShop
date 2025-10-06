package org.example.service.impl;


import com.example.entity.ProductImage;
import com.example.entity.ProductSalePropertyKey;
import com.example.entity.ProductSalePropertyValue;
import com.example.entity.ProductSpu;
import org.example.mapper.ProductSpuMapper;
import org.example.service.ProductImageService;
import org.example.service.ProductSalePropertyKeyService;
import org.example.service.ProductSalePropertyValueService;
import org.example.service.ProductSpuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author tangguo
 * @since 2025-10-07
 */
@Service
public class ProductSpuServiceImpl extends ServiceImpl<ProductSpuMapper, ProductSpu> implements ProductSpuService {
    @Autowired
    private ProductImageService imageService;
    @Autowired
    private ProductSalePropertyKeyService salePropertyKeyService;
    @Autowired
    private ProductSalePropertyValueService salePropertyValueService;

    @Transactional
    @Override
    public void saveProductSpu(@RequestBody ProductSpu productSpu) {
        //b.保存SPU的基本信息
        save(productSpu);
        //c.保存SPU的image信息
        Long spuId = productSpu.getId();
        List<ProductImage> productImageList = productSpu.getProductImageList();
        if(!CollectionUtils.isEmpty(productImageList)){
            for (ProductImage productImage : productImageList) {
                productImage.setProductId(spuId);
            }
            imageService.saveBatch(productImageList);
        }
        //d.保存SPU的销售属性信息
        List<ProductSalePropertyKey> salePropertyKeyList = productSpu.getSalePropertyKeyList();
        if(!CollectionUtils.isEmpty(salePropertyKeyList)){
            //实际场景下循环不了几次
            for (ProductSalePropertyKey propertyKey : salePropertyKeyList) {
                propertyKey.setProductId(spuId);
                List<ProductSalePropertyValue> propertyValueList = propertyKey.getSalePropertyValueList();
                if(!CollectionUtils.isEmpty(propertyValueList)){
                    for (ProductSalePropertyValue propertyValue : propertyValueList) {
                        propertyValue.setProductId(spuId);
                        propertyValue.setSalePropertyKeyName(propertyKey.getSalePropertyKeyName());
                    }
                    salePropertyValueService.saveBatch(propertyValueList);
                }
            }
            salePropertyKeyService.saveBatch(salePropertyKeyList);
        }
    }
}
