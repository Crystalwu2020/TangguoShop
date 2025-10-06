package com.example.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class SpuImageVo {
    @ApiModelProperty(value = "图片名称")
    private String imageName;
    @ApiModelProperty(value = "图片路径")
    private String imageUrl;

}
