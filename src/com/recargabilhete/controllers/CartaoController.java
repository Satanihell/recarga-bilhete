package com.recargabilhete.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.recargabilhete.entity.Cartao;
import com.recargabilhete.entity.Usuario;
import com.recargabilhete.repository.CartaoRepository;
import com.recargabilhete.repository.UsuarioRepository;

@Controller
@RequestMapping("/adicionar-cartao")
public class CartaoController {
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView cartao() { 
		ModelAndView mv = new ModelAndView("adicionar-cartao");
		Iterable<Cartao> iter = cartaoRepository.findAll();
		Cartao cartao = new Cartao();
		mv.addObject("cartao", cartao);
		mv.addObject("lista", iter);
		return mv;
	}
	
	@RequestMapping(method=RequestMethod.POST, name = "salvar")
	public ModelAndView salvar(@ModelAttribute("cartao") Cartao cartao, HttpServletRequest req) {
		long idUsuario = Long.parseLong(req.getParameter("idUsuario"));
		Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);
		cartao.setUsuario(usuario);
		
		System.out.format("IdCartao: %s Usuario: %s TipoCartao: %s DataExpedicao: %s Status: %s", cartao.getIdCartao(), cartao.getUsuario(), cartao.getTipoCartao(), cartao.getDataExpedicao(), cartao.getStatusCartao());
		
		cartaoRepository.save(cartao);
		ModelAndView mv = new ModelAndView("adicionar-cartao");
		mv.addObject("cartao", new Cartao());
		return mv;
	}

}
