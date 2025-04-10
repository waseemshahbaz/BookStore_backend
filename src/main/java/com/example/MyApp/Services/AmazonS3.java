package com.example.MyApp.Services;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
public class AmazonS3 {
    @Autowired
    AmazonS3Client amazonS3Client;
    String bucketName= "bookscoverpictures";

    public String uploadPicture(MultipartFile file) {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String todayDate = dateTimeFormatter.format(LocalDate.now());

            InputStream inputStream = file.getInputStream();
            ObjectMetadata objectMetadata = new ObjectMetadata();

            Random random = new Random();
            int randomNumber = random.nextInt(1000);

            String filename = file.getOriginalFilename() + todayDate + randomNumber;
            filename = filename.replaceAll("[^a-zA-Z0-9]+","_");
            objectMetadata.setContentType("image/jpeg");
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, filename, inputStream, objectMetadata);
            amazonS3Client.putObject(putObjectRequest   );
            return "https://" + bucketName + ".s3.amazonaws.com/" + filename;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

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
