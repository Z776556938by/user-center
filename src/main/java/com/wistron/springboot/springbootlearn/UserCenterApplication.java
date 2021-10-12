package com.wistron.springboot.springbootlearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.function.Consumer;


@MapperScan("com.wistron")
@SpringBootApplication
public class UserCenterApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserCenterApplication.class, args);
	}

}
