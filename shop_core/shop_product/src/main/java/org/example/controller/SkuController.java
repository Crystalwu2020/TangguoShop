package org.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.entity.ProductSalePropertyKey;
import com.example.entity.ProductSalePropertyValue;
import com.example.result.RetVal;
import org.example.entity.ProductSaleValueVo;
import org.example.mapper.ProductSalePropertyKeyMapper;
import org.example.service.BaseSalePropertyService;
import org.example.service.ProductSalePropertyKeyService;
import org.example.service.ProductSalePropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 库存单元表 前端控制器
 * </p>
 *
 * @author tangguo
 * @since 2025-10-07
 */
@RestController
@RequestMapping("/product")
@CrossOrigin
public class SkuController {
    @Autowired
    ProductSalePropertyKeyMapper propertyKeyMapper;




    //http://127.0.0.1:8000/product/querySalePropertyByProductId/21
    @GetMapping("/querySalePropertyByProductId/{spuId}")
    public RetVal querySalePropertyByProductId(@PathVariable Long spuId){
    /*    LambdaQueryWrapper<com.example.entity.ProductSalePropertyValue> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ProductSalePropertyValue::getProductId,productId);
        List<ProductSalePropertyValue> list = productSalePropertyValueService.list(wrapper);*/

       List<ProductSalePropertyKey> productSalePropertyKeyList=propertyKeyMapper.querySalePropertyByProductId(spuId);
        return RetVal.ok(productSalePropertyKeyList);
    }


}

