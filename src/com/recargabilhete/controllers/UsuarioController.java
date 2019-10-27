package com.recargabilhete.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.recargabilhete.entity.Usuario;
import com.recargabilhete.repository.UsuarioRepository;

@Controller
public class UsuarioController {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@RequestMapping("/usuario")
	public ModelAndView usuario() { 
		ModelAndView mv = new ModelAndView("usuario");
		Iterable<Usuario> iter = usuarioRepository.findAll();
		Usuario usuario = new Usuario();
		
		mv.addObject("usuario", usuario);
		mv.addObject("lista", iter);
		return mv;
	}
	
	@RequestMapping("/salvar")
	public ModelAndView salvar(@ModelAttribute("usuario") Usuario usuario) {
		System.out.format("Nome: %s", usuario.getNome());
		usuarioRepository.save(usuario);
		ModelAndView mv = new ModelAndView("usuario");
		mv.addObject("usuario", new Usuario());
		return mv;
	}

}
