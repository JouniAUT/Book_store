package hh.sof03.kirjakauppa.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> { //UserRepository käyttää valmista CrudRepository metodikirjastoa
	User findByUsername(String username); //lisätään myös käyttäjänimen mukaan etsiminen
	
	List<User> findUserByUsername(String username);
}
