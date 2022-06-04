package com.hwanld.EntripAPI;

import OperationSystemAssignment.PriorityQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class EntripApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(EntripApiApplication.class, args);
	}
}
