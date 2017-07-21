package kr.or.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import kr.or.reservation.domain.Category;
import kr.or.reservation.service.CategoryService;

@Controller
@RequestMapping("/categorys")
public class CategoryController {
	
	CategoryService catergoryservice;
	
	@Autowired
    public void setCatergoryservice(CategoryService catergoryservice) {
		this.catergoryservice = catergoryservice;
	}

	// Post 방식을 사용시, 넘겨받은 name을 통해 category 등록 후, redirect 
    @PostMapping()
    public String insert(Model model,@RequestParam String name){
    	Category category = new Category(name);
    	catergoryservice.insert(category);
    	return "redirect:/categorys";
    }
    
    
    // url 로 id를 받아 해당 데이터를 삭제
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id") long id){
    	catergoryservice.deleteById(id);
    }
    
    // delete와 마찬가지로 url로 id를 받고, body 부분에 json으로 id / name을 받아 수정함.
    @PutMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") long id, @RequestBody Category category){
    	catergoryservice.update(category);
    }
 

}