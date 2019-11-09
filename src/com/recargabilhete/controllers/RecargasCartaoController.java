package com.recargabilhete.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.recargabilhete.entity.Cartao;
import com.recargabilhete.entity.RecargasCartao;
import com.recargabilhete.entity.TipoRecarga;
import com.recargabilhete.repository.CartaoRepository;
import com.recargabilhete.repository.RecargasCartaoRepository;
import com.recargabilhete.repository.TipoRecargaRepository;

@Controller
@RequestMapping("/adicionar-recargas-cartao")
public class RecargasCartaoController {
	
	@Autowired
	private RecargasCartaoRepository recargasCartaoRepository;
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private TipoRecargaRepository tipoRecargaRepository;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView recargasCartao() { 
		ModelAndView mv = new ModelAndView("adicionar-recargas-cartao");
		Iterable<RecargasCartao> iter = recargasCartaoRepository.findAll();
		RecargasCartao recargasCartao = new RecargasCartao();
		mv.addObject("recargasCartao", recargasCartao);
		mv.addObject("lista", iter);
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, name = "salvar")
	public ModelAndView salvar(@ModelAttribute("recargasCartao") RecargasCartao recargasCartao, HttpServletRequest req) {
		
		long idCartao = Long.parseLong(req.getParameter("idCartao"));
		Cartao cartao = cartaoRepository.findById(idCartao).orElse(null);
		recargasCartao.setCartao(cartao);
		
		long idTipoRecarga = Long.parseLong(req.getParameter("idTipoRecarga"));
		TipoRecarga tipoRecarga = tipoRecargaRepository.findById(idTipoRecarga).orElse(null);
		recargasCartao.setTipoRecarga(tipoRecarga);
		
		recargasCartaoRepository.save(recargasCartao);
		ModelAndView mv = new ModelAndView("adicionar-recargas-cartao");
		mv.addObject("recargasCartao", new RecargasCartao());
		return mv;
	}

}