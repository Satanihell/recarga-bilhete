package com.recargabilhete.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.recargabilhete.entity.TipoRecarga;
import com.recargabilhete.repository.TipoRecargaRepository;

@Controller
public class TipoRecargaController {
	
	@Autowired
	private TipoRecargaRepository tipoRecargaRepository;
	
	@RequestMapping(path = "/adicionar-tipo-recarga", method=RequestMethod.GET)
	public ModelAndView tipoRecarga() { 
		ModelAndView mv = new ModelAndView("adicionar-tipo-recarga");
		Iterable<TipoRecarga> iter = tipoRecargaRepository.findAll();
		TipoRecarga tipoRecarga = new TipoRecarga();
		mv.addObject("tipoRecarga", tipoRecarga);
		mv.addObject("lista", iter);
		return mv;
	}
	
	@RequestMapping(path = "/adicionar-tipo-recarga", method = RequestMethod.POST, name = "salvar")
	public ModelAndView salvar(@ModelAttribute("tipoRecarga") TipoRecarga tipoRecarga) {
		tipoRecargaRepository.save(tipoRecarga);
		ModelAndView mv = new ModelAndView("adicionar-tipo-recarga");
		mv.addObject("tipoRecarga", new TipoRecarga());
		return mv;
	}
	
	@RequestMapping(path = "/pesquisar-tipo-recarga", method = RequestMethod.GET)
	public ModelAndView tipoRecargaPesquisar() {
		ModelAndView mv = new ModelAndView("pesquisar-tipo-recarga");
		Iterable<TipoRecarga> iter = tipoRecargaRepository.findAll();
		TipoRecarga tipoRecarga = new TipoRecarga();
		mv.addObject("tipoRecarga", tipoRecarga);
		mv.addObject("lista", iter);
		return mv;
	}

	@RequestMapping(path = "/pesquisar-tipo-recarga", method = RequestMethod.GET, params = "idTipoRecarga")
	public ModelAndView pesquisarTipoRecargaId(@ModelAttribute("tipoRecarga") TipoRecarga tipoRecargaDigitado, @RequestParam("idTipoRecarga") long idTipoRecarga) {
		ModelAndView mv = new ModelAndView("pesquisar-tipo-recarga");
//		long idTipoRecarga = Long.parseLong(req.getParameter("idTipoRecarga"));
		TipoRecarga tipoRecarga = tipoRecargaRepository.findById(idTipoRecarga).orElse(null);
		System.out.print(tipoRecarga);
		mv.addObject("tipoRecarga", tipoRecarga);
		mv.addObject("lista", tipoRecarga);
		return mv;
	}

	@RequestMapping(path = "/pesquisar-tipo-recarga/remover/{idTipoRecarga}", method = RequestMethod.POST, name = "remover")
	public String removerTipoRecarga(@PathVariable(name = "idTipoRecarga") String idTipoRecarga) {
		tipoRecargaRepository.deleteById(Long.parseLong(idTipoRecarga));
		return "redirect:/pesquisar-tipo-recarga";
	}

	@RequestMapping(path = "/pesquisar-tipo-recarga/alterar-tipo-recarga/{idTipoRecarga}", method = RequestMethod.GET, name = "alterar")
	public ModelAndView alterarTipoRecarga(@PathVariable(name = "idTipoRecarga") String idTipoRecargaStr) {
		ModelAndView mv = new ModelAndView("alterar-tipo-recarga");
		long idTipoRecarga = Long.parseLong(idTipoRecargaStr);
		TipoRecarga tipoRecarga = tipoRecargaRepository.findById(idTipoRecarga).orElse(null);
		mv.addObject("tipoRecarga", tipoRecarga);
		return mv;
	}

	@RequestMapping(path = "/pesquisar-tipo-recarga/alterar-tipo-recarga/{idTipoRecarga}", method = RequestMethod.POST, name = "alterar")
	public String submitAlterarTipoRecarga(@ModelAttribute(name = "tipoRecarga") TipoRecarga tipoRecarga) {
		tipoRecargaRepository.save(tipoRecarga);
		return "redirect:/pesquisar-tipo-recarga";
	}

}
