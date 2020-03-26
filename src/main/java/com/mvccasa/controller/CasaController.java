package com.mvccasa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mvccasa.model.Casa;
import com.mvccasa.repository.CasaFilter;
import com.mvccasa.services.CasaServices;

@Controller
@RequestMapping("/casas")
public class CasaController {

	@Autowired
	private CasaServices services;
	

	
	@RequestMapping
	public ModelAndView index(@ModelAttribute("filter") CasaFilter filter) {
    	
		Iterable<Casa> casas  = services.search(filter); 
		
		ModelAndView mv = new ModelAndView("/Casa/Casas");
		
		mv.addObject("casas", casas);
		return mv;

	}
	
	@RequestMapping("/create")
    public ModelAndView create() {
    	ModelAndView mv = new ModelAndView("Casa/CreateCasa");
    	mv.addObject(new Casa());
        return mv;
    }
	
	//SAVE
    @RequestMapping(method = RequestMethod.POST)
    public String save(@Validated Casa casa, Errors errors, RedirectAttributes attributes) {
    	
    	if(errors.hasErrors()) {
    		return "Casa/CreateCasa";
    	}
    	try {
    		services.save(casa);
    		attributes.addFlashAttribute("menssage", "Título salvo com sucesso!");
    		return "redirect:/casas/create";
		} catch (IllegalArgumentException e) {
			return "Casa/CreateCasa";
		}
	    
    	
    }
    
    @RequestMapping("{id}")
    public ModelAndView edit(@PathVariable("id") Casa casa) {
    	ModelAndView mv = new ModelAndView("/Casa/CreateCasa");
		mv.addObject(casa);
		return mv;
	}
    //DELETE
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Casa casa) {
    	services.delete(casa);
        return "redirect:/casas";
    }
    
}
