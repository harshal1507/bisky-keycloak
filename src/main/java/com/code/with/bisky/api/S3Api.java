package com.code.with.bisky.api;


import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.code.with.bisky.dto.*;
import com.code.with.bisky.service.StripeService;
import com.code.with.bisky.service.s3.AwsS3Service;
import com.stripe.model.Subscription;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping("/public/s3")
@AllArgsConstructor
public class S3Api {


    private AwsS3Service awsS3Service;


    @PostMapping(value = "/upload-object",consumes = "multipart/form-data")
    @ResponseBody
    public FileResponseRecord uploadObject(@RequestParam(value = "file") MultipartFile file,
                                           @RequestParam String fileName,
                                           @RequestParam String bucketName,
                                           @RequestParam String key) throws IOException {


        String newKey = String.format("%s/%s", key, fileName);
        byte[] bytes = file.getBytes();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectMetadata objectMetadata=new ObjectMetadata();
        objectMetadata.setContentLength(bytes.length);
        PutObjectResult putObjectResult = awsS3Service.uploadObject(bucketName, newKey, byteArrayInputStream, objectMetadata);
        return new FileResponseRecord(true,null);
    }



    @GetMapping(value = "/pre-signed-url")
    @ResponseBody
    public FileResponseRecord getPreSignedUrl( @RequestParam String bucketName,
                                           @RequestParam String key) throws IOException {

        return new FileResponseRecord(true,awsS3Service.getPreSignedUrl(bucketName, key));
    }

    @DeleteMapping(value = "/remove-object")
    @ResponseBody
    public FileResponseRecord deleteObject( @RequestParam String bucketName,
                                               @RequestParam String key) throws IOException {

        awsS3Service.deleteObject(bucketName,key);
        return new FileResponseRecord(true,null);
    }
}
