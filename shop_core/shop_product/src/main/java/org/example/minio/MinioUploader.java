package org.example.minio;

import edu.umd.cs.findbugs.annotations.CreatesObligation;
import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import io.minio.errors.*;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Component
@EnableConfigurationProperties(MinioProperties.class)
@CrossOrigin
public class MinioUploader {

    @Autowired
    private MinioProperties minioProperties;

    @Autowired
    MinioClient minioClient;


    @Bean
    public MinioClient minioClient() throws Exception {
        // 使用MinIO服务的URL，端口，Access key和Secret key创建一个MinioClient对象
        MinioClient minioClient = new MinioClient(minioProperties.getEndpoint(),
                minioProperties.getAccessKey(), minioProperties.getSecretKey());
        // 检查存储桶是否已经存在
        boolean isExist = minioClient.bucketExists(minioProperties.getBucketName());
        if(isExist) {
            System.out.println("Bucket already exists.");
        } else {
            // 创建一个名为asiatrip的存储桶，用于存储照片的zip文件。
            minioClient.makeBucket(minioProperties.getBucketName());
        }
        return minioClient;
    }

    public  String upload(MultipartFile file) throws Exception {

        InputStream inputStream= file.getInputStream();
        String prefix = UUID.randomUUID().toString().replaceAll("-", "");
        String originalFilename = file.getOriginalFilename();
        String suffix = FilenameUtils.getExtension(originalFilename);
        String fileName=prefix+"."+suffix;

        // FileInputStream inputStream = new FileInputStream("E:\\desktop\\341\\1.jpg");
        //文件上传时候需要的参数 文件可用大小与文件上传多少内容 文件全部上传
        PutObjectOptions options=new PutObjectOptions(inputStream.available(),-1);
        // 使用putObject上传一个文件到存储桶中。

        minioClient.putObject(minioProperties.getBucketName(),fileName,inputStream,options);
        //http://192.168.121.128:9000/java011/new.jpg
        String retUrl=minioProperties.getEndpoint()+"/"+minioProperties.getBucketName()+"/"+fileName;
        System.out.println("上传成功");
        return retUrl;
    }

    public static void main(String[] args){
        String a="girl.jpg";
        String extension = FilenameUtils.getExtension(a);
        String subString=a.substring(a.lastIndexOf("."));
        System.out.println(subString);
    }

}
