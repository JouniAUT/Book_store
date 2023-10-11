package hh.sof03.kirjakauppa.domain;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> { //UserRepository käyttää valmista CrudRepository metodikirjastoa
	User findByUsername(String username); //lisätään myös käyttäjänimeen mukaan etsiminen
}
