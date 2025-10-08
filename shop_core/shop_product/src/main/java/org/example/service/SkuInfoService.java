package org.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.SkuInfo;


/**
 * <p>
 * 库存单元表 服务类
 * </p>
 *
 * @author tangguo
 * @since 2025-10-07
 */
public interface SkuInfoService extends IService<SkuInfo> {

    void saveSkuInfo(SkuInfo skuInfo);
}
