package com.proiectps.JobBoard;

import com.proiectps.JobBoard.model.User;
import com.proiectps.JobBoard.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

public class JobBoardApplication {
	public static void main(String[] args) {
		SpringApplication.run(JobBoardApplication.class, args);

	}
	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return args -> {
		User newUser = new User();
		newUser.setUsername("admin");
		userRepository.save(newUser);
		};
	}

}
