package com.drore.cloud.tdp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration
@MapperScan("com.drore.cloud.tdp.entity")
@EnableScheduling
public class DroreNetworkUtilApplication {

	public static void main(String[] args) {
		SpringApplication.run(DroreNetworkUtilApplication.class, args);
	}
}
