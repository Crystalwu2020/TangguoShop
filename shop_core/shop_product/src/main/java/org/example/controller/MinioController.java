package org.example.controller;

import com.example.result.RetVal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class MinioController {

    @PostMapping("minioUpload")
    public RetVal minioUpload(@RequestPart("avatar")MultipartFile avatar,
                              @RequestPart("avatar")MultipartFile life,
                              @RequestPart("avatar")MultipartFile secret
                              ){

        return RetVal.ok();
    }

}
