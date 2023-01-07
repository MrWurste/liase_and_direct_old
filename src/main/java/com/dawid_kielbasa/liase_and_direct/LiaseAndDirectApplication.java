package com.dawid_kielbasa.liase_and_direct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Default main class
 */
@SpringBootApplication
public class LiaseAndDirectApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiaseAndDirectApplication.class, args);
	}

}
