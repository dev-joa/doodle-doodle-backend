package dev.joa.doodledoodlebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DoodleDoodleBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoodleDoodleBackendApplication.class, args);
	}

}
