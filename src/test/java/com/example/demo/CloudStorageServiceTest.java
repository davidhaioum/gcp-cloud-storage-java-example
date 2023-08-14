package com.example.demo;

import com.example.demo.service.CloudStorageService;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

@TestPropertySource(locations = "classpath:application.yaml")
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CloudStorageServiceTest {
    @Value("${gcp.storage.bucket-name}")
    private String bucketName;

    @Mock
    private Storage storage;

    @InjectMocks
    private CloudStorageService cloudStorageService;

    @Test
    public void uploadFileTest() throws IOException {
        assertNotNull(bucketName);

        // // Create a mock Blob object
        BlobId blobId = BlobId.of(bucketName, "text.txt");
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        // Mock the upload method
        when(storage.create(eq(blobInfo), any(byte[].class))).thenReturn(null);

        // Upload the file to the bucket
        cloudStorageService.uploadFile("BUCKET NAME", "text.txt");

        // Verify that the upload method was called with the correct parameters
        verify(storage).create(eq(blobInfo), any(byte[].class));
    }
}
