package org.example.controller;


import com.example.config.MybatisPlusConfig;
import com.example.result.RetVal;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.example.entity.PlatformPropertyKey;
import org.example.entity.PlatformPropertyValue;
import org.example.service.PlatformPropertyKeyService;
import org.example.service.PlatformPropertyValueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 属性表 前端控制器
 * </p>
 *
 * @author tangguo
 * @since 2025-08-25
 */
@RestController
@RequestMapping("/product")
@Import(MybatisPlusConfig.class)
@CrossOrigin
public class PlatformPropertyController {
    @Autowired
    private PlatformPropertyKeyService platformPropertyKeyService;

    @Autowired
    private PlatformPropertyValueService platformPropertyValueService;

    private static final Logger logger = LoggerFactory.getLogger(PlatformPropertyController.class);

/*   @GetMapping("/getPlatformPropertyByCategoryId/{category1Id}/{category2Id}/{category3Id}")
    public RetVal getPropertyValueList(@PathVariable Long category1Id,
                                       @PathVariable Long category2Id,
                                       @PathVariable Long category3Id
                                     ){


        List<PlatformPropertyKey> platformPropertyKeyList = platformPropertyKeyService
                                          .getPlatformPropertyByCategoryId2(category1Id,category2Id,category3Id);
        return RetVal.ok(platformPropertyKeyList);
    }*/


    @GetMapping("/getPlatformPropertyByCategoryId/{category1Id}/{category2Id}/{category3Id}")
    public RetVal getPropertyValueList(@PathVariable Long category1Id,
                                       @PathVariable Long category2Id,
                                       @PathVariable Long category3Id
    ){


        List<PlatformPropertyKey> platformPropertyKeyList = platformPropertyKeyService
                .getPlatformPropertyByCategoryId(category1Id,category2Id,category3Id);
           logger.info(""+platformPropertyKeyList.toString());
        return RetVal.ok(platformPropertyKeyList);
    }


     //2,根据平台属性keyId 查询属性值
     @GetMapping("/getPlatformValueByKey/{platformKeyId}")
     public RetVal getPropertyValueListByKey(@PathVariable Long platformKeyId){

         LambdaQueryWrapper<PlatformPropertyValue> platformPropertyValueWrapper = new LambdaQueryWrapper<>();
         platformPropertyValueWrapper.eq(PlatformPropertyValue::getPropertyKeyId,platformKeyId);
         List<PlatformPropertyValue> platformPropertyValuelist = platformPropertyValueService.list(platformPropertyValueWrapper);
         return RetVal.ok(platformPropertyValuelist);
     }


      //3,保存平台属性
    //http://192.168.1.24:8810/product/savePlatformProperty
    //1，保存平台属性key
    //2，保存平台属性value
     @PostMapping("/savePlatformProperty")
     @Transactional(rollbackFor = Exception.class)
     public RetVal savePlatformProperty(@RequestBody PlatformPropertyKey platformPropertyKey){
        platformPropertyKeyService.savePlatformProperty(platformPropertyKey);
         return RetVal.ok();
     }
}

