package hh.sof03.kirjakauppa.webcontrol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import hh.sof03.kirjakauppa.domain.BookRepository;

@Controller
public class BookController {
	
	@Autowired
	BookRepository bookRepository;

	
	@GetMapping("/index")
	String showIndex(Model model) {
		
		model.addAttribute("books", bookRepository.findAll());
		return "index";
	}
}
