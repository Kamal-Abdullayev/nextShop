package com.nextShop.auth;

import com.nextShop.auth.model.User;
import com.nextShop.auth.service.security.AccessTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
public class AuthApplication implements CommandLineRunner{

	private final AccessTokenManager accessTokenManager;
	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		User user = User.builder()
				.name("Kamal")
				.surname("Abdullayev")
				.username("Kamal.Abdullayev")
				.email("kamal@gmail.com")
				.phoneNumber("+994505554466")
				.password("test")
				.build();

		System.out.println(accessTokenManager.generate(user));
	}
}





