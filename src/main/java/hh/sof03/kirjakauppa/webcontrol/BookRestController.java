package hh.sof03.kirjakauppa.webcontrol;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.sof03.kirjakauppa.domain.Book;
import hh.sof03.kirjakauppa.domain.BookRepository;

@CrossOrigin
@Controller //Luodaan RESTful-service Controller ja ResponseBody- annotaatioilla
public class BookRestController {

	@Autowired //Yhdistetään BookRepository-luokka @Autowired annotaatiolla
	private BookRepository bookRepository;
	
	@RequestMapping(value = "/books", method = RequestMethod.GET) //luodaan RequestMapping metodi, joka vastaa /books-endpointiin
	public @ResponseBody List<Book> bookRestList() {
		return (List<Book>) bookRepository.findAll(); //metodi palauttaa selaimelle kaikki Book-luokan oliot JSON:ksi muutettuna
	}
	
	@RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> bookRestFind(@PathVariable("id")Long bookId) {
		return bookRepository.findById(bookId);
	}
}
