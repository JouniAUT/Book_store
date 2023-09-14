package hh.sof03.kirjakauppa.webcontrol;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

	
	@GetMapping("/index")
	String showIndex() {
		return "index";
	}
}
