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
	private CategoryRepository categoryRepository;
	
	@Test
	public void findByCategoryName() {
		List<Category> categories = categoryRepository.findByName("Thriller");
		
		assertThat(categories).hasSize(1);
		assertThat(categories.get(0).getName()).isEqualTo("Thriller");
	}
	
	@Test
	public void createNewCategory() {
		Category category = new Category("Detective");
		categoryRepository.save(category);
		assertThat(category.getCategoryid()).isNotNull();
	}
	
	@Test
	public void deleteAllCategories() {
		categoryRepository.deleteAll();
		assertThat(categoryRepository.count()).isEqualTo(0);
	}
	
	@Test
	public void deleteFirstCategory() {
		categoryRepository.deleteById((long )1);
		assertThat(categoryRepository.existsById((long) 2));
		assertThat(categoryRepository.existsById((long) 3));
		assertThat(categoryRepository.count()).isLessThan(3);
	}
}
	


