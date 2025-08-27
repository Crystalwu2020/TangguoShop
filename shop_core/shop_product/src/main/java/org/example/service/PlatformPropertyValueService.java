package org.example.service;

import org.example.entity.PlatformPropertyKey;
import org.example.entity.PlatformPropertyValue;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 属性值表 服务类
 * </p>
 *
 * @author tangguo
 * @since 2025-08-25
 */
public interface PlatformPropertyValueService extends IService<PlatformPropertyValue> {

    List<PlatformPropertyValue> getPlatformValueList(Long category1Id, Long category2Id, Long category3Id);


}
