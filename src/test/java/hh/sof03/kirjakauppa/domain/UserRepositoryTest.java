package hh.sof03.kirjakauppa.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository; //liitetään repository-luokkka testiluokkaan
	
	@Test
	public void findUserByUsername() { //luodaan metodi jolla etsitään käyttäjänimellä olio
		List<User> users = userRepository.findUserByUsername("user"); //etsitään käyttäjänimellä
			
		assertThat(users).hasSize(1); //tarkistetaan äsken luodun listan koko
		assertThat(users.get(0).getId()).isEqualTo(1); //tarkistetaan että listan ensimmäisen olion id on 1
		
	}
	
	@Test
	public void createNewUser() { //luodaan uusi käyttäjä testiuokassa
		User user = new User("Jouni", "$2a$10$tjWdBTsn.0AOZrEZ9Mm1J.b32rBDhig4GgSEUh4dBjJn0TTTdhEL6", "jouni@jouni.net", "ADMIN");
		userRepository.save(user); //tallennetaan käyttäjä
		assertThat(user.getId()).isNotNull(); //tarkistetaan, että äsken luodulla käyttäjällä on myös id
	}
	
	@Test
	public void deleteAllUsers() { //luodaan metodi jolla poistetaan kaikki käyttäjät testiluokassa
		userRepository.deleteAll();
		
		assertThat(userRepository.count()).isEqualTo(0); //tarkistetaan, että kaikki käyttäjät on poistettu
	}
	
	@Test
	public void deleteFirstUser() { //luodaan metodi jolla poistetaan listan ensimmäinen käyttäjä
		userRepository.deleteById((long)1); //poistetaan id:n mukaan listan ensimmäinen käyttäjä
		assertThat(userRepository.findById((long)1).isEmpty()); //tarkistetaan, että äsken poistetun käyttäjän id on tyhjä
		assertThat(userRepository.count()).isEqualTo(1); //tarkistetaan, että jäljellä olevien käyttäjien määrä on 1
	}
}
