package org.example.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.ProductSalePropertyKey;
import org.example.entity.ProductSaleValueVo;

import java.util.List;

/**
 * <p>
 * spu销售属性 Mapper 接口
 * </p>
 *
 * @author tangguo
 * @since 2025-10-07
 */
public interface ProductSalePropertyKeyMapper extends BaseMapper<ProductSalePropertyKey> {

   /* List<ProductSaleValueVo> selectInnerSalePropertyValue();*/

    default List<ProductSalePropertyKey> querySalePropertyByProductId(Long spuId) {
        return null;
    }
}
