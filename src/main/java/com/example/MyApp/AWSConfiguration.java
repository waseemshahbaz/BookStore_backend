package com.example.MyApp;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AWSConfiguration {

    @Value("${aws.accessKey}")
    private String accesskey;

    @Value("${aws.secretKey}")
    private String secretKey;

    @Bean
    public BasicAWSCredentials basicAWSCredentials () {
        return new BasicAWSCredentials(accesskey, secretKey);
    }

    @Bean
    AmazonS3Client amazonS3Client(AWSCredentials awsCredentials) {
        AmazonS3Client amazonS3Client = new AmazonS3Client(awsCredentials);
        amazonS3Client.setRegion(Region.getRegion(Regions.AP_SOUTH_1));
        return  amazonS3Client;
    }





}
