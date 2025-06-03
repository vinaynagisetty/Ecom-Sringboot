package com.vinay.nagisetty.SpringbootEmbarkx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.vinay.nagisetty.SpringbootEmbarkx.model")
public class SpringbootEmbarkxApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootEmbarkxApplication.class, args);
	}

}
