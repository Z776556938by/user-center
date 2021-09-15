package com.wistron.springboot.springbootlearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;


@MapperScan("com.wistron")
@SpringBootApplication
public class UserCenterApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserCenterApplication.class, args);
	}
}
