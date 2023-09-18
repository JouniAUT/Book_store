package hh.sof03.kirjakauppa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.sof03.kirjakauppa.domain.Book;
import hh.sof03.kirjakauppa.domain.BookRepository;

@SpringBootApplication
public class KirjakauppaApplication {
	private static final Logger log = LoggerFactory.getLogger(KirjakauppaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KirjakauppaApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository) {
		return (args) -> {
			log.info("save a couple of books");
			bookRepository.save(new Book ("Tuntematon sotilas", "Väinö Linna", 1954, "9789510445785", 19.95));
			bookRepository.save(new Book ("Vuonna 1984", "George Orwell", 1949, "9789510404478", 14.50));
			log.info("fetch all books");
			
		};
	}
	
}
