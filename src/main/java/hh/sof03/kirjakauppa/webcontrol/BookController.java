package hh.sof03.kirjakauppa.webcontrol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.sof03.kirjakauppa.domain.Book;
import hh.sof03.kirjakauppa.domain.BookRepository;
import hh.sof03.kirjakauppa.domain.CategoryRepository;

@Controller
public class BookController {
	
	@Autowired
	BookRepository bookRepository;
	@Autowired
	CategoryRepository categoryRepository;
	
	/*@RequestMapping(value = "index")
	String showIndex(Model model) {
		model.addAttribute("books", bookRepository.findAll());
		return "index";
	}*/
	
	@RequestMapping(value = "/booklist")
	public String bookList(Model model) {
		model.addAttribute("books", bookRepository.findAll());
		return "booklist";
	}
	
	@RequestMapping(value = "/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", categoryRepository.findAll()); //lisätään categoryrepository kirjanluonti-metodin käyttöön
		return "addbook";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book) {
		bookRepository.save(book);	
		return "redirect:/booklist";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')") //Lisätään URL:n arvailun estämiseksi. Käyttäjä tarvitsee admin roolin käyttääkseen ko. toimintoa
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		bookRepository.deleteById(bookId);
		return "redirect:../booklist";
	}
	
	@RequestMapping(value = "/edit/{id}")
	public String showEditBook(@PathVariable("id")Long bookId, Model model) {
		model.addAttribute("book", bookRepository.findById(bookId));
		model.addAttribute("categories", categoryRepository.findAll());
		return "editbook";
	}
	
}
