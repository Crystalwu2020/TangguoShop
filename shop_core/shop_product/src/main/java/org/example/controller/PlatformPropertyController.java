package org.example.controller;


import com.atguigu.config.MybatisPlusConfig;
import com.atguigu.result.RetVal;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.example.entity.PlatformPropertyKey;
import org.example.entity.PlatformPropertyValue;
import org.example.service.PlatformPropertyKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
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

    @GetMapping("/getPlatformPropertyByCategoryId/{category1Id}/{category2Id}/{category3Id}")
    public RetVal getPropertyValueList(@PathVariable Long category1Id,
                                       @PathVariable Long category2Id,
                                       @PathVariable Long category3Id
                                     ){


        List<PlatformPropertyKey> platformPropertyKeyList = platformPropertyKeyService
                                          .getPlatformPropertyByCategoryId2(category1Id,category2Id,category3Id);
        return RetVal.ok(platformPropertyKeyList);
    }

}

