package org.example.service.impl;


import com.example.entity.*;
import org.example.mapper.ProductSalePropertyKeyMapper;
import org.example.mapper.SkuInfoMapper;
import org.example.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 库存单元表 服务实现类
 * </p>
 *
 * @author tangguo
 * @since 2025-10-07
 */
@Service
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper, SkuInfo> implements SkuInfoService {
    @Autowired
  private ProductSalePropertyValueService salePropertyValueService;

    @Autowired
    private SkuPlatformPropertyValueService skuPlatformPropertyValueService;
    @Autowired
    private ProductSalePropertyKeyMapper propertyKeyMapper;

    @Autowired
    private SkuImageService skuImageService;
    @Autowired
    SkuSalePropertyValueService skuSalePropertyValueService;
    @Autowired
    private ProductImageService imageService;
    @Autowired
    private SkuInfoService skuInfoService;

    @Transactional
    @Override
    public void saveSkuInfo(SkuInfo skuInfo) {
        //a,保存sku平台的基本信息
        save(skuInfo);
        //b,保存SKU平台属性信息
        Long skuId = skuInfo.getId();
        Long productId = skuInfo.getProductId();

        List<SkuPlatformPropertyValue> skuPlatformPropertyValueList = skuInfo.getSkuPlatformPropertyValueList();
        if(!CollectionUtils.isEmpty(skuPlatformPropertyValueList)){
            for(SkuPlatformPropertyValue platformPropertyValue:skuPlatformPropertyValueList){
                platformPropertyValue.setId(skuId);
              //  skuPlatformPropertyValueService.save(platformPropertyValue);
            }
            skuPlatformPropertyValueService.saveBatch(skuPlatformPropertyValueList);
        }

        //保存SKU销售属性的值
        List<SkuSalePropertyValue> skuSalePropertyValueList = skuInfo.getSkuSalePropertyValueList();
        if(!CollectionUtils.isEmpty(skuSalePropertyValueList)){
            for(SkuSalePropertyValue skuSalePropertyValue:skuSalePropertyValueList){
                skuSalePropertyValue.setSkuId(skuId);
                skuSalePropertyValue.setProductId(productId);
            }
            skuSalePropertyValueService.saveBatch(skuSalePropertyValueList);
        }



        //保存图片信息
        List<SkuImage> skuImageList = skuInfo.getSkuImageList();
        if(!CollectionUtils.isEmpty(skuImageList)){
            for(SkuImage skuImage:skuImageList){
                skuImage.setSkuId(skuId);
            }
            skuImageService.saveBatch(skuImageList);
        }



    }
}
