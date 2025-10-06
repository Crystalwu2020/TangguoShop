package com.example.vo;

import lombok.Data;

import java.util.List;
@Data
public class CategoryVo {
    //当前分类id
    private Long categoryId;
    //分类名称
    private String categoryName;
    //子分类信息
    private List<CategoryVo> categoryChild;
}
