package org.example.service.impl;

import org.example.entity.PlatformPropertyValue;
import org.example.mapper.PlatformPropertyValueMapper;
import org.example.service.PlatformPropertyValueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 属性值表 服务实现类
 * </p>
 *
 * @author tangguo
 * @since 2025-08-25
 */
@Service
public class PlatformPropertyValueServiceImpl extends ServiceImpl<PlatformPropertyValueMapper, PlatformPropertyValue> implements PlatformPropertyValueService {

    @Override
    public List<PlatformPropertyValue> getPlatformValueList(Long category1Id, Long category2Id, Long category3Id) {
        return null;
    }
}
