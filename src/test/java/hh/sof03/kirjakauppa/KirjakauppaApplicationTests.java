package hh.sof03.kirjakauppa;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof03.kirjakauppa.webcontrol.BookController;
import hh.sof03.kirjakauppa.webcontrol.BookRestController;
import hh.sof03.kirjakauppa.webcontrol.CategoryController;
import hh.sof03.kirjakauppa.webcontrol.UserDetailServiceImplementation;

@ExtendWith(SpringExtension.class) //JUnit5-versio JUnit-frameworkista
@SpringBootTest
class KirjakauppaApplicationTests {
	
	@Autowired								//liitetään controlleriluokat testeihin
	private BookController bookController;
	
	@Autowired
	private BookRestController bookRestController;
	
	@Autowired
	private CategoryController categoryController;
	
	@Autowired
	private UserDetailServiceImplementation userDetailServiceImplementation;
	
	@Test
	public void contextLoads() { // luodaan assertThat-lauseilla smoketestit kaikille controller-luokille
		assertThat(bookController).isNotNull();
		assertThat(bookRestController).isNotNull();
		assertThat(categoryController).isNotNull();
		assertThat(userDetailServiceImplementation).isNotNull();
	}

}
