package com.cjh.online;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cjh.online.restaurant.mapper")
public class OnlinerestaurantApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlinerestaurantApplication.class, args);
	}

}
