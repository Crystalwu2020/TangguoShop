package org.example.service;

import org.example.entity.PlatformPropertyKey;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 属性表 服务类
 * </p>
 *
 * @author tangguo
 * @since 2025-08-25
 */
public interface PlatformPropertyKeyService extends IService<PlatformPropertyKey> {

    List<PlatformPropertyKey> getPlatformPropertyByCategoryId1(Long category1Id, Long category2Id, Long category3Id);

    public List<PlatformPropertyKey> getPlatformPropertyByCategoryId2(Long category1Id, Long category2Id, Long category3Id);

}
