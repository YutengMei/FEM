package com.ym.mall.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class BucketServiceImpl implements BucketService {

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    @Override
    public List<Bucket> getBucketList() {
        return null;
    }

    @Override
    public boolean validateBucket(String bucketName) {
        return false;
    }

    @Override
    public void getObjectFromBucket(String bucketName, String objectName) throws IOException {

    }

    @Override
    public void uploadFile(String keyName, MultipartFile file) throws IOException {
        PutObjectResult putObjectResult = s3Client.putObject(bucketName, keyName, file.getInputStream(), null);
        log.info(String.valueOf(putObjectResult.getMetadata()));
    }


    @Override
    public void createBucket(String bucket) {

    }

    @Override
    public String generateUrl(String fileName, HttpMethod httpMethod) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 1);
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, fileName).withMethod(httpMethod).withExpiration(new Date(System.currentTimeMillis() + 240000));
        URL preSignedURL = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
        return preSignedURL.toString();
    }



}
