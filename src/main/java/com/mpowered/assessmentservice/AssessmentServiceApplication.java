package com.mpowered.assessmentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = {"com.mpowered"})
@EntityScan(basePackages = "com.mpowered")
@EnableJpaRepositories(basePackages = "com.mpowered")
public class AssessmentServiceApplication {

	private static ApplicationContext context;
	
	public static void main(String[] args) {
		//SpringApplication.run(AssessmentServiceApplication.class, args);
		context = new SpringApplicationBuilder(AssessmentServiceApplication.class).properties(
				"spring.config.name" +
						":commons-application" +
						",application"
		).build().run(args);
	}

}
