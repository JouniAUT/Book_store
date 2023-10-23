package hh.sof03.kirjakauppa.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class) //liitetään JUnit5
@DataJpaTest
public class CategoryRepositoryTest {
	
	@Autowired
	private CategoryRepository categoryRepository; //liitetään repository-luokkka testiluokkaan
	
	@Test
	public void findByCategoryName() { //luodaan metodi, jolla etsitään nimellä kategoria-olio
		List<Category> categories = categoryRepository.findByName("Thriller"); //etsitään kategorian nimellä
		
		assertThat(categories).hasSize(1); //tarkistetaan juuri luodun listan koko
		assertThat(categories.get(0).getName()).isEqualTo("Thriller"); //tarkistetaan listan ensimmäisen olion nimi
	}
	
	@Test
	public void createNewCategory() { //luodaan metodi jolla luodaan uusi kategoria
		Category category = new Category("Detective"); //luodaan Detective-niminen kategoria
		categoryRepository.save(category); //tallennetaan uusi kategoria
		assertThat(category.getCategoryid()).isNotNull(); // tarkistetaan, että uudella kategorialla on id
	}
	
	@Test
	public void deleteAllCategories() { //luodaan metodi jolla poistetaan kaikki kategoria oliot
		categoryRepository.deleteAll(); //poistetaan kaikki kategoriat
		assertThat(categoryRepository.count()).isEqualTo(0); //tarkistetaan, että kaikki kategoriat on poistettu
	}
	
	@Test
	public void deleteFirstCategory() { // luodaan metodi, jolla poistettan listan ensimmäinen kategoria
		categoryRepository.deleteById((long )1); //poistetaan ensimmäinen kategoria
		assertThat(categoryRepository.existsById((long) 2)); //tarkistetaan, että kategoriat 2 ja 3 ovat vielä tallella
		assertThat(categoryRepository.existsById((long) 3));
		assertThat(categoryRepository.count()).isGreaterThan(1);
		assertThat(categoryRepository.count()).isLessThan(3); //tarkistetaan, että kategorioita on enemmän kuin 1, mutta vähemmän kuin 3
	}
}
	


