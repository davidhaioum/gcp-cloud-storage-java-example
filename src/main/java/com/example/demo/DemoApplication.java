package com.example.demo;

import com.example.demo.service.CloudStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	@Autowired
	private CloudStorageService cloudStorageService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws IOException {
		cloudStorageService.uploadFile("BUCKET NAME", "text.txt");
	}
}
