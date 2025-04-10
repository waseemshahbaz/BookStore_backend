package com.example.MyApp.Controllers;

import io.github.cdimascio.dotenv.Dotenv;

public class AwsCredentialsProvider {
    private static final Dotenv dotenv = Dotenv.load();

    public static String getAccessKey() {
        return dotenv.get("AWS_ACCESS_KEY");
    }

    public static String getSecretKey() {
        return dotenv.get("AWS_SECRET_KEY");
    }
}
