package com.elgendy.playgrounds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.elgendy.playgrounds.controller", "com.elgendy.playgrounds.repository", "com.elgendy.playgrounds.service", "configs"})
public class PlaygroundsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaygroundsApplication.class, args);
	}
}
