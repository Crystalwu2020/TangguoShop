package org.example.mapper;

import org.example.entity.PlatformPropertyKey;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.entity.PlatformPropertyValue;

import java.util.List;

/**
 * <p>
 * 属性表 Mapper 接口
 * </p>
 *
 * @author tangguo
 * @since 2025-08-25
 */
public interface PlatformPropertyKeyMapper extends BaseMapper<PlatformPropertyKey> {

    List<PlatformPropertyKey> getPropertyKeyByCategoryId(Long category1Id, Long category2Id, Long category3Id);



    List<PlatformPropertyKey> getPropertyByCategoryId(Long category1Id, Long category2Id, Long category3Id);
}
