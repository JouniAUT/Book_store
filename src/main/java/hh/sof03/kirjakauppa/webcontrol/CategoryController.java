package hh.sof03.kirjakauppa.webcontrol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.sof03.kirjakauppa.domain.Category;
import hh.sof03.kirjakauppa.domain.CategoryRepository;

@Controller
public class CategoryController {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	
	@RequestMapping(value = "/categorylist") //luodaan metodi, joka näyttää kaiki Category-luokan oliot
	public String categoryList(Model model) {
		model.addAttribute("categories", categoryRepository.findAll());
		return "categorylist";
	}
	
	@RequestMapping(value = "/addcat") //luodaan metodi joka luo uuden kategorian
	public String addCategory(Model model) {
		model.addAttribute("category", new Category());
		return "addcategory";
	}
	
	@RequestMapping(value = "/savecategory", method = RequestMethod.POST) //luodaan metodi, joka tallentaa juuri luodun kategoria-olion
	public String saveCategory(Category category) {
		categoryRepository.save(category);
		return "redirect:/categorylist";
		
	}
}
