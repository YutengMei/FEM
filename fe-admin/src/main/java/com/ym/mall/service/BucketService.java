package com.ym.mall.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.model.Bucket;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BucketService {
    List<Bucket> getBucketList();

    boolean validateBucket(String bucketName);

    void getObjectFromBucket(String bucketName, String objectName) throws IOException;

    void uploadFile(String keyName, MultipartFile file) throws IOException;

    void createBucket(String bucket);

    String generateUrl(String fileName, HttpMethod httpMethod);


}
