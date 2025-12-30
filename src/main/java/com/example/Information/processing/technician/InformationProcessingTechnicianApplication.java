package com.example.Information.processing.technician;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class InformationProcessingTechnicianApplication {

	public static void main(String[] args) {
		SpringApplication.run(InformationProcessingTechnicianApplication.class, args);
	}

}
