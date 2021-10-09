package com.meli.technicalevaluation.meliconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class MeliConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeliConfigApplication.class, args);
	}

}
