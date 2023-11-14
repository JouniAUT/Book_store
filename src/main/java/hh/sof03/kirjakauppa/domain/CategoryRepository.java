package hh.sof03.kirjakauppa.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long>{
//käytetään valmiiksi luotua CrudRepository-kirjastoa, josta CategoryRepository saa toimintoja käyttöönsä

	List<Category> findByName(String name);
	
}	
