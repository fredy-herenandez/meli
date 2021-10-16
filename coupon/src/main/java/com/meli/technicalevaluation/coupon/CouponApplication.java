package com.meli.technicalevaluation.coupon;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
public class CouponApplication {

	public static void main(String[] args) {

		System.setProperty("logName", "service-meli-coupon");
		SpringApplication.run(CouponApplication.class, args);
		log.info("Start service coupon.");

	}

}