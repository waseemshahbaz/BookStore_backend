package com.example.MyApp.Services;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class AmazonS3 {
    @Autowired
    AmazonS3Client amazonS3Client;
    String bucketName= "bookscoverpictures";

    public boolean objectExists(String fileName) {
        return amazonS3Client.doesObjectExist(bucketName, fileName);
    }

    public byte[] downloadObject(String fileName) {
        try {
            S3Object s3Object = amazonS3Client.getObject(bucketName, fileName);
            S3ObjectInputStream inputStream = s3Object.getObjectContent();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            return StreamUtils.copyToByteArray(inputStream);
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
