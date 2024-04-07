package com.jungche.happyschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.jungche.happyschool.repository")
@EntityScan("com.jungche.happyschool.model")
public class HappyschoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(HappyschoolApplication.class, args);
	}

}
