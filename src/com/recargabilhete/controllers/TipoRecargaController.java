package com.recargabilhete.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.recargabilhete.entity.TipoRecarga;
import com.recargabilhete.repository.TipoRecargaRepository;

@Controller
@RequestMapping("/adicionar-tipo-recarga")
public class TipoRecargaController {
	
	@Autowired
	private TipoRecargaRepository tipoRecargaRepository;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView tipoRecarga() { 
		ModelAndView mv = new ModelAndView("adicionar-tipo-recarga");
		Iterable<TipoRecarga> iter = tipoRecargaRepository.findAll();
		TipoRecarga tipoRecarga = new TipoRecarga();
		mv.addObject("tipoRecarga", tipoRecarga);
		mv.addObject("lista", iter);
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, name = "salvar")
	public ModelAndView salvar(@ModelAttribute("tipoRecarga") TipoRecarga tipoRecarga) {
		tipoRecargaRepository.save(tipoRecarga);
		ModelAndView mv = new ModelAndView("adicionar-tipo-recarga");
		mv.addObject("tipoRecarga", new TipoRecarga());
		return mv;
	}

}
