package com.example.demo.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
//@PropertySource("classpath:application.properties")
public class CloudStorageService {
    public void uploadFile(
            String bucketName,
            String filePath
    ) throws IOException {
        Path pathToFileToUpload = Paths.get(filePath);

        GoogleCredentials credentials;
        credentials = GoogleCredentials.getApplicationDefault();

        // Use the credentials to authenticate to the cloud storage service
        Storage storage = StorageOptions
                .newBuilder()
                .setCredentials(credentials)
                .build()
                .getService();
        BlobId blobId = BlobId.of(bucketName, filePath);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        storage.create(blobInfo, Files.readAllBytes(pathToFileToUpload));
    }
}
