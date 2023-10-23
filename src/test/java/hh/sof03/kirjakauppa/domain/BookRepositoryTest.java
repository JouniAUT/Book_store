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
public class BookRepositoryTest {

	@Autowired
	private BookRepository bookRepository; //liitetään repository-luokkka testiluokkaan
	
	
	@Test
	public void findByTitleReturnsAuthor() { //luodaan metodi, jolla etsitään kirjan nimellä olio
		List<Book> books = bookRepository.findByTitle("Vuonna 1984"); //etsitään kirjan nimellä
		
		assertThat(books).hasSize(1); //tarkistetaan äsken luodun listan koko
		assertThat(books.get(0).getAuthor()).isEqualTo("George Orwell"); //tarkistetaan että listan ensimmäisen olion id on 1
	}
	
	@Test
	public void createNewBook() { //luodaan uusi kirja testiluokassa
		Book book = new Book("Taru Sormusten Herrasta", "J.R.R Tolkien", 1954, "978-951-033-33-72", 25.55, null	);
		bookRepository.save(book); //tallennetaan kirja
		assertThat(book.getId()).isNotNull(); //tarkistetaan, että äsken luodulla kirjalla on id
		
	}
	
	@Test
	public void deleteAllBooks() { //luodaan metodi jolla poistetaan kaikki kirjat
		bookRepository.deleteAll();
		
		assertThat(bookRepository.count()).isEqualTo(0); //tarkistetaan, että kaikki kirjat tietoineen on poistettu
			
	}
	
	@Test
	public void deleteFirstBook() { //luodaan metodi jolla poistetaan listan ensimmäinen kirja
		bookRepository.deleteById((long) 1); //poistettan ensimmäinen kirja
		assertThat(bookRepository.count()).isEqualTo(1); //tarkistetaan, että listaan on jäänyt yksi kirja jäljelle poiston jälkeen
		
	}

}
