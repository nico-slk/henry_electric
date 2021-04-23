package com.electric.henryElect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HenryElectApplication {

	public static void main(String[] args) {
		SpringApplication.run(HenryElectApplication.class, args);

	}

}
