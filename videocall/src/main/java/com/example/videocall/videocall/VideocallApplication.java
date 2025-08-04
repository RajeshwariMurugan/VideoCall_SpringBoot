package com.example.videocall.videocall;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.videocall.videocall.user.User;
import com.example.videocall.videocall.user.UserService;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.videocall.videocall.user")
@EntityScan(basePackages = "com.example.videocall.videocall.user")
public class VideocallApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideocallApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UserService service) {
		return args -> {
			User user1 = new User();
			user1.setUsername("user1");
			user1.setEmail("user1@gmail.com");
			user1.setPassword("1234");
			user1.setStatus("online");
			service.register(user1);

		};
	}
}


