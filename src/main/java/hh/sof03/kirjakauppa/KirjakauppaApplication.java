package hh.sof03.kirjakauppa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.sof03.kirjakauppa.domain.Book;
import hh.sof03.kirjakauppa.domain.BookRepository;
import hh.sof03.kirjakauppa.domain.Category;
import hh.sof03.kirjakauppa.domain.CategoryRepository;
import hh.sof03.kirjakauppa.domain.User;
import hh.sof03.kirjakauppa.domain.UserRepository;


@SpringBootApplication
public class KirjakauppaApplication {
	private static final Logger log = LoggerFactory.getLogger(KirjakauppaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KirjakauppaApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository, CategoryRepository categoryRepository ,UserRepository userRepository) { //lisätään bookrepository ja categoryrepository commandlinen käyttöön
		return (args) -> {
			
			
			log.info("create few new categories");
			Category scifi = new Category("Scifi");
			categoryRepository.save(scifi);
			Category thriller = new Category("Thriller");
			categoryRepository.save(thriller);
			Category historical	= new Category("Historical fiction");
			categoryRepository.save(historical);
			
			log.info("fetch all categories");
			for(Category category : categoryRepository.findAll()) {
				log.info(category.toString());
			}
			
			log.info("save a couple of books");
			bookRepository.save(new Book ("Tuntematon sotilas", "Väinö Linna", 1954, "978-9510-445-785", 19.95, historical));
			bookRepository.save(new Book ("Vuonna 1984", "George Orwell", 1949, "978-9510-404-478", 14.59, scifi));
			
			User user1 = new User("user", "$2a$10$DTEkVNCxHGf.To8Zni12S.DKIP0SVkHvfMNfnW8prCyocDNOym7FK","USER");
			userRepository.save(user1);
			User user2 = new User("admin", "$2a$10$q2kgQZeQWzhwg8.PxHjhWeFqu4OIriqpfOCxCeATDZfVNgS4Sp2fe" ,"ADMIN");
			userRepository.save(user2);
			
			log.info("fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}
		};
	}
	
	
	
}
