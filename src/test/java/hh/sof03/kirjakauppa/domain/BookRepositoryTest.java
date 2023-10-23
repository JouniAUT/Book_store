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
	private BookRepository bookRepository;
	
	
	@Test
	public void findByTitleReturnsAuthor() {
		List<Book> books = bookRepository.findByTitle("Vuonna 1984");
		
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("George Orwell");
	}
	
	@Test
	public void createNewBook() {
		Book book = new Book("Taru Sormusten Herrasta", "J.R.R Tolkien", 1954, "978-951-033-33-72", 25.55, null	);
		bookRepository.save(book);
		assertThat(book.getId()).isNotNull();
		
	}
	
	@Test
	public void deleteAllBooks() {
		bookRepository.deleteAll();
		assertThat(bookRepository.count()).isEqualTo(0);
			
	}
	
	@Test
	public void deleteFirstBook() {
		bookRepository.deleteById((long) 1);
		assertThat(bookRepository.count()).isEqualTo(1);
		
	}

}
