package com.nextShop.auth;

import com.nextShop.auth.model.User;
//import com.nextShop.auth.service.security.AccessTokenManager;
import com.nextShop.auth.model.proporties.SecurityProperties;
import com.nextShop.auth.service.UserService;
import com.nextShop.auth.service.security.AccessTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@SpringBootApplication
public class AuthApplication implements CommandLineRunner{

	private final AccessTokenManager accessTokenManager;
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		User user = User.builder()
				.id(2L)
				.firstName("Kamal")
				.lastName("Abdullayev")
				.username("Kamal2.Abdullayev")
				.email("kamal2@gmail.com")
				.phoneNumber("+994505554475")
				.password(passwordEncoder.encode("test"))
				.accountNonExpired(true)
				.isEnabled(true)
				.isEnabled(true)
				.credentialNonExpired(true)
				.isCredentialsNonExpired(true)
				.roleId(1L)
				.build();

		userService.saveUser(user);

		User myUser = userService.getUserByEmail("kamal@gmail.com");
		System.out.println(myUser);


//		final String token = accessTokenManager.generate(user);
//		System.out.println(accessTokenManager.read(token).get("username", String.class));
	}
}





