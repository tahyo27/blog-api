package com.blog.practiceapi;

import com.blog.practiceapi.config.StrDataConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@EnableConfigurationProperties(StrDataConfig.class)
@SpringBootApplication
public class PracticeapiApplication {
	public static void main(String[] args) {
		SpringApplication.run(PracticeapiApplication.class, args);
	}

}
