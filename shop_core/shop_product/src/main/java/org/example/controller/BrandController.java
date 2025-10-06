package org.example.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.result.RetVal;
import org.apache.commons.io.FilenameUtils;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.example.entity.BaseBrand;
import org.example.minio.MinioUploader;
import org.example.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 品牌表 前端控制器
 * </p>
 *
 * @author tangguo
 * @since 2025-10-04
 */
@RestController
@RequestMapping("/product/brand")
@CrossOrigin
public class BrandController {
    @Autowired
    private BrandService brandService;

    //http://127.0.0.1:8000/product/brand/queryBrandByPage/1/10
    //1.分页查询 http://192.168.121.129/product/brand/queryBrandByPage/1/10
    @GetMapping("queryBrandByPage/{pageNo}/{pageSize}")
   public RetVal queryBrandByPage(@PathVariable Long pageNo,@PathVariable Long pageSize){
       IPage<BaseBrand> page = new Page<>(pageNo,pageSize);
       brandService.page(page,null);
       return RetVal.ok(page);
   }

    //http://127.0.0.1/product/brand
    //2.添加品牌
    @PostMapping
    public RetVal saveBrand(@RequestBody BaseBrand brand) {
        brandService.save(brand);
        return RetVal.ok();
    }

    //http://127.0.0.1/product/brand/4
    //3.根据id查询品牌信息
    @GetMapping("{brandId}")
    public RetVal getById(@PathVariable Long brandId) {
        BaseBrand brand = brandService.getById(brandId);
        return RetVal.ok(brand);
    }

    //4.更新品牌信息
    @PutMapping
    public RetVal updateBrand(@RequestBody BaseBrand brand) {
        brandService.updateById(brand);
        return RetVal.ok();
    }

    //5.删除品牌信息
    @DeleteMapping("{brandId}")
    public RetVal remove(@PathVariable Long brandId) {
        brandService.removeById(brandId);
        return RetVal.ok();
    }

    //6.查询所有的品牌
    @GetMapping("getAllBrand")
    public RetVal getAllBrand() {
        List<BaseBrand> brandList = brandService.list(null);
        return RetVal.ok(brandList);
    }

    //7 文件上传
    //7.文件上传 http://api.gmall.com/product/brand/fileUpload
    @PostMapping("fileUpload1")
    public RetVal fileUpload1(MultipartFile file) throws Exception {
        //需要一个配置文件告诉fastdfs在哪里
        String configFilePath = this.getClass().getResource("/tracker.conf").getFile();
        //初始化
        ClientGlobal.init(configFilePath);
        //创建trackerClient 客户端
        TrackerClient trackerClient = new TrackerClient();
        //用trackerClient获取连接
        TrackerServer trackerServer = trackerClient.getConnection();
        //创建StorageClient1
        StorageClient1 storageClient1 = new StorageClient1(trackerServer, null);
        //对文件实现上传
        String originalFilename = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(originalFilename);
        //这个异常的原因是当时
        String path = storageClient1.upload_appender_file1(file.getBytes(), extension, null);
        //拼接路径 http://192.168.121.128:8888/group1/M00/00/01/wKh5gGRiHAeEfdnWAAAAAIx0Xh4285.jpg
        //作业
        return RetVal.ok();
    }

     @Autowired
    private MinioUploader minioUploader;

    @PostMapping("fileUpload")
    public RetVal fileUpload(MultipartFile file) throws Exception {
        String retUrl = minioUploader.upload(file);
        return RetVal.ok(retUrl);
    }


}

