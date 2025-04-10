package com.example.MyApp;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.example.MyApp.Controllers.AwsCredentialsProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AWSConfiguration {

    @Bean
    public BasicAWSCredentials basicAWSCredentials () {

        String accessKey = AwsCredentialsProvider.getAccessKey();
        String secretKey = AwsCredentialsProvider.getSecretKey();
        return new BasicAWSCredentials(accessKey, secretKey);
    }

    @Bean
    AmazonS3Client amazonS3Client(AWSCredentials awsCredentials) {
        AmazonS3Client amazonS3Client = new AmazonS3Client(awsCredentials);
        amazonS3Client.setRegion(Region.getRegion(Regions.AP_SOUTH_1));
        return  amazonS3Client;
    }





}
