package com.nextShop.auth;

import com.nextShop.auth.models.Users;
import com.nextShop.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
public class AuthApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

	private final UserRepository userRepository;
	@Override
	public void run(String... args) throws Exception {
		Users user = new Users(
				"name", "surname", "username", "ok", 1L,
				"test@gmail.com", "pass", "pass"
		);
		userRepository.save(user);
	}
}
