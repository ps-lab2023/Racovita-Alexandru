package com.proiectps.JobBoard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proiectps.JobBoard.model.User;
import com.proiectps.JobBoard.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@SpringBootApplication
@CrossOrigin(origins = "http://localhost:4200/")
public class JobBoardApplication {
	public static void main(String[] args) {
		SpringApplication.run(JobBoardApplication.class, args);

	}
/*	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return args -> {
		User newUser = new User();
		newUser.setUsername("admin");
		userRepository.save(newUser);
		};
	}
*/
}
