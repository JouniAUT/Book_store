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
	private UserRepository userRepository;
	
	@Test
	public void findUserByUsername() {
		List<User> users = userRepository.findUserByUsername("user");
			
		assertThat(users).hasSize(1);
		assertThat(users.get(0).getId()).isEqualTo(1);
		
	}
	
	@Test
	public void createNewUser() {
		User user = new User("Jouni", "$2a$10$tjWdBTsn.0AOZrEZ9Mm1J.b32rBDhig4GgSEUh4dBjJn0TTTdhEL6", "jouni@jouni.net", "ADMIN");
		userRepository.save(user);
		assertThat(user.getId()).isNotNull();
	}
	
	@Test
	public void deleteAllUsers() {
		userRepository.deleteAll();
		
		assertThat(userRepository.count()).isEqualTo(0);
	}
	
	@Test
	public void deleteFirstUser() {
		userRepository.deleteById((long)1);
		assertThat(userRepository.findById((long)1).isEmpty());
		assertThat(userRepository.count()).isEqualTo(1);
	}
}
