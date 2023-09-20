package hh.sof03.kirjakauppa.webcontrol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.sof03.kirjakauppa.domain.Book;
import hh.sof03.kirjakauppa.domain.BookRepository;

@Controller
public class BookController {
	
	@Autowired
	BookRepository bookRepository;
	

	
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
		return "addbook";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book) {
		bookRepository.save(book);
		return "redirect:booklist";
	}
	
}
