package com.ym.mall.controller;

import com.amazonaws.HttpMethod;
import com.amazonaws.regions.Regions;
import com.ym.mall.common.api.CommonResult;
import com.ym.mall.service.BucketServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/s3")
@Slf4j
public class S3Controller {

    @Autowired
    private BucketServiceImpl bucketService;

    @Value("${aws.s3.productCateImagePath}")
    private String imagePath;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @PostMapping(path = "/uploadImage", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        bucketService.uploadFile(imagePath + file.getOriginalFilename(), file);
        return "File uploaded";
    }

    @RequestMapping(path = "/generateUrl/{fileName}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Map<String, String>> uploadFile(@PathVariable String fileName) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSS");
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
        UUID id = UUID.randomUUID();
        String fileFullPath = imagePath + LocalDateTime.now().format(formatter) + id.getMostSignificantBits() + "." + fileType;
        String generateUrl = bucketService.generateUrl(fileFullPath, HttpMethod.PUT);
        String imageUrl = "https://"+ bucketName + ".s3." + Regions.US_EAST_2.getName() + ".amazonaws.com/" + fileFullPath;
        Map<String, String> map = new HashMap<>();
        map.put("host", generateUrl);
        map.put("dir", imageUrl);
        return CommonResult.success(map);
    }

}
