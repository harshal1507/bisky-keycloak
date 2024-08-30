package com.code.with.bisky.service.s3;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.Date;


@Service
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class AwsS3ServiceImpl  implements  AwsS3Service{

    @Value("${aws.access.keyId}")
    private String accessKeyId;


    @Value("${aws.secret.access.key}")
    private String secretAccessKey;

    @Value("${aws.s3.url}")
    private String s3Url;
    @Override
    public AmazonS3 getClient() {

        AWSCredentials awsCredentials = new BasicAWSCredentials(secretAccessKey,accessKeyId);

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withPathStyleAccessEnabled(true)
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(s3Url,"eu2"))
                .build();
    }

    @Override
    public String getPreSignedUrl(String bucketName, String key) {

        AmazonS3 amazonS3 = getClient();
        Date expiration = new Date();
        long exTimeMillis = Instant.now().toEpochMilli();
        exTimeMillis += 1000 * 60 * 2; // 2 minutes expiration from the current time
        expiration.setTime(exTimeMillis);
        return amazonS3.generatePresignedUrl(bucketName,key,expiration, HttpMethod.GET).toString();
    }

    @Override
    public byte[] getObject(String bucketName, String key) throws IOException {

        AmazonS3 amazonS3 = getClient();
        S3Object s3Object = amazonS3.getObject(bucketName, key);
        S3ObjectInputStream s3ObjectInputStream = s3Object.getObjectContent();
        return IOUtils.toByteArray(s3ObjectInputStream);
    }

    @Override
    public boolean deleteObject(String bucketName, String key) {


        AmazonS3 amazonS3 = getClient();
        amazonS3.deleteObject(bucketName, key);
        return true;
    }

    @Override
    public PutObjectResult uploadObject(String bucketName, String key, InputStream inputStream, ObjectMetadata metadata) {

        AmazonS3 amazonS3 = getClient();
        return amazonS3.putObject(bucketName,key,inputStream,metadata);
    }
}
