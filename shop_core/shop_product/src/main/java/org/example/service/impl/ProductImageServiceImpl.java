package org.example.service.impl;


import com.example.entity.ProductImage;
import org.example.mapper.ProductImageMapper;
import org.example.service.ProductImageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品图片表 服务实现类
 * </p>
 *
 * @author tangguo
 * @since 2025-10-07
 */
@Service
public class ProductImageServiceImpl extends ServiceImpl<ProductImageMapper, ProductImage> implements ProductImageService {

}
