package hh.sof03.kirjakauppa;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableMethodSecurity(securedEnabled = true) //Lisätään @PreAuthorize annotaation kanssa URL:n arvailun estämiseksi

public class WebSecurityConfiguration {

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception{
		http
		.authorizeHttpRequests(authorize -> authorize
				.requestMatchers(antMatcher("/css/**")).permitAll()
				.anyRequest().authenticated()
		)
		.formLogin(formlogin -> formlogin
			.defaultSuccessUrl("/booklist", true)
			.permitAll()
		)
		.logout(logout -> logout
				.permitAll()
		);
		return http.build();
				
	}
	
	@Bean
	public UserDetailsService userDetailService() {
		List<UserDetails> users = new ArrayList<UserDetails>();
		
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		UserDetails user1 = User
			.withUsername("user")
			.password(passwordEncoder.encode("user"))
			.roles("USER")
			.build();
		
		users.add(user1);
	
	UserDetails user2 = User
			.withUsername("admin")
			.password(passwordEncoder.encode("admin"))
			.roles("ADMIN")
			.build();
	
		users.add(user2);
		
		return new InMemoryUserDetailsManager(users);
	}
}
