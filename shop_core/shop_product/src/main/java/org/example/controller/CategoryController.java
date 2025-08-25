package org.example.controller;


import com.atguigu.result.RetVal;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.entity.BaseCategory1;
import org.example.entity.BaseCategory2;
import org.example.entity.BaseCategory3;
import org.example.service.BaseCategory1Service;
import org.example.service.BaseCategory2Service;
import org.example.service.BaseCategory3Service;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 一级分类表 前端控制器
 * </p>
 *
 * @author tangguo
 * @since 2025-08-25
 */
@RestController
@RequestMapping("/product")
@CrossOrigin
public class CategoryController {
    @Autowired
    private BaseCategory1Service baseCategory1Service;

    @Autowired
    private BaseCategory2Service baseCategory2Service;

    @Autowired
    private BaseCategory3Service baseCategory3Service;

    @GetMapping("/getCategory1")
    public RetVal getCategory1(){
        List<BaseCategory1> listCategory1 = baseCategory1Service.list(null);
        System.out.println("getCategory1 = " + listCategory1);
        return RetVal.ok(listCategory1);
    }

    @GetMapping("/getCategory2/{category1Id}")
    public RetVal getCategory2(@PathVariable Long category1Id){
        LambdaQueryWrapper<BaseCategory2> BaseCategory2Wrapper = new LambdaQueryWrapper<>();
       BaseCategory2Wrapper.eq(BaseCategory2::getCategory1Id, category1Id);

        List<BaseCategory2> listCategory2 = baseCategory2Service.list(BaseCategory2Wrapper);
        System.out.println("listCategory2 = " + listCategory2);
        return RetVal.ok(listCategory2);
    }


    @GetMapping("/getCategory3/{category2Id}")
    public RetVal getCategory3(@PathVariable Long category2Id){
        LambdaQueryWrapper<BaseCategory3> baseCategory3Wrapper = new LambdaQueryWrapper<>();
        baseCategory3Wrapper.eq(BaseCategory3::getCategory2Id,category2Id);
        List<BaseCategory3> baseCategory3List = baseCategory3Service.list(baseCategory3Wrapper);
        System.out.println("baseCategory3List = " + baseCategory3List);
       return RetVal.ok(baseCategory3List);
    }
}

