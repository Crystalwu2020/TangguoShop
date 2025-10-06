package org.example.service.impl;

import org.example.entity.PlatformPropertyKey;
import org.example.entity.PlatformPropertyValue;
import org.example.mapper.PlatformPropertyKeyMapper;
import org.example.service.PlatformPropertyKeyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.service.PlatformPropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 属性表 服务实现类
 * </p>
 *
 * @author tangguo
 * @since 2025-08-25
 */
@Service
public class PlatformPropertyKeyServiceImpl extends ServiceImpl<PlatformPropertyKeyMapper, PlatformPropertyKey>
        implements PlatformPropertyKeyService {
    @Autowired
    private PlatformPropertyValueService platformPropertyValueService;


    //@Override
 /*   public List<PlatformPropertyKey> getPlatformPropertyByCategoryId1(Long category1Id, Long category2Id, Long category3Id) {
       //1,根据产品分类id，和去查询key id
        List<PlatformPropertyKey> platformPropertyKeyList=baseMapper.getPropertyKeyByCategoryId(category1Id,category2Id,category3Id);


        //2,根据keyId 去查询value
        for (PlatformPropertyKey platformPropertyKey:platformPropertyKeyList) {
            LambdaQueryWrapper<PlatformPropertyValue> platformPropertyKeyLambdaQueryWrapper = new LambdaQueryWrapper<>();
           platformPropertyKeyLambdaQueryWrapper.eq(PlatformPropertyValue::getPropertyKeyId, platformPropertyKey.getId());
            List<PlatformPropertyValue> list = platformPropertyValueService.list(platformPropertyKeyLambdaQueryWrapper);
            platformPropertyKey.setPropertyValueList(list);

        }
        return platformPropertyKeyList;
    }*/


    @Override
    public List<PlatformPropertyKey> getPlatformPropertyByCategoryId(Long category1Id, Long category2Id, Long category3Id) {

        return baseMapper.getPropertyByCategoryId(category1Id, category2Id, category3Id);
    }


    @Transactional
    @Override
    public void savePlatformProperty(PlatformPropertyKey platformPropertyKey) {

        //1,保存key的值
        save(platformPropertyKey);

        //2，保存value 值
        List<PlatformPropertyValue> propertyValueList = platformPropertyKey.getPropertyValueList();
        if (!CollectionUtils.isEmpty(propertyValueList)) {
            for (PlatformPropertyValue propertyValue : propertyValueList) {
                propertyValue.setPropertyKeyId(platformPropertyKey.getId());
            }
            platformPropertyValueService.saveBatch(propertyValueList);
        }
    }





}
