package org.example.minio;

import io.minio.MinioClient;
import io.minio.PutObjectOptions;

import java.io.FileInputStream;

public class FileUploader {
    public static void main(String[] args) throws Exception {
        // 使用MinIO服务的URL，端口，Access key和Secret key创建一个MinioClient对象
        MinioClient minioClient = new MinioClient("http://192.168.121.128:9000", "enjoy6288", "enjoy6288");
        // 检查存储桶是否已经存在
        boolean isExist = minioClient.bucketExists("java011");
        if(isExist) {
            System.out.println("Bucket already exists.");
        } else {
            // 创建一个名为asiatrip的存储桶，用于存储照片的zip文件。
            minioClient.makeBucket("java202510");
        }
        FileInputStream inputStream = new FileInputStream("E:\\desktop\\341\\1.jpg");
        //文件上传时候需要的参数 文件可用大小与文件上传多少内容 文件全部上传
        PutObjectOptions options=new PutObjectOptions(inputStream.available(),-1);
        // 使用putObject上传一个文件到存储桶中。

        minioClient.putObject("java202510","new.jpg",inputStream,options);
        System.out.println("上传成功");
    }
}
