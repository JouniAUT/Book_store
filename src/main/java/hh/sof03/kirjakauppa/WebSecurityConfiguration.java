package hh.sof03.kirjakauppa;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(securedEnabled = true) //Lisätään @PreAuthorize annotaation kanssa URL:n arvailun estämiseksi
public class WebSecurityConfiguration {
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception{ //määritellään URL-polut suojatuiksi
		http
		.authorizeHttpRequests(authorize -> authorize
				.requestMatchers(antMatcher("/css/**")).permitAll() //annetaan css.tiedostolle lupatoimia uloskirjauksen yhteydessä
				.anyRequest().authenticated()
		)
		.formLogin(formlogin -> formlogin //luodaan automaattiinen login-sivu, annetaan login-sivu kaikkien käyttäjien käyttöön
			.defaultSuccessUrl("/booklist", true) //ohjataan käyttäjä booklist-sivulle kirjautumisen jälkeen
			.permitAll()
		)
		.logout(logout -> logout
				.permitAll() //salli uloskirjaus kaikille käyttäjille
		);
		return http.build();
				
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}


	
	/*@Bean
	public UserDetailsService userDetailService() { 
		List<UserDetails> users = new ArrayList<UserDetails>(); //luodaan lista käyttäjiä varten
		
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder(); //luodaan salasanakryptays
		
		UserDetails user1 = User //tavallinen käyttäjä
			.withUsername("user")
			.password(passwordEncoder.encode("user"))
			.roles("USER")
			.build();
		
		users.add(user1);
	
	UserDetails user2 = User //admin-käyttäjä
			.withUsername("admin")
			.password(passwordEncoder.encode("admin"))
			.roles("ADMIN")
			.build();
	
		users.add(user2);
		
		return new InMemoryUserDetailsManager(users);
	}*/
}
