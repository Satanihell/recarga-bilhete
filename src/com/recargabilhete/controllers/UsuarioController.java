package com.recargabilhete.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.recargabilhete.entity.Usuario;
import com.recargabilhete.repository.UsuarioRepository;

@Controller
@RequestMapping("/adicionar-usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView adicionarUsuario() {
		ModelAndView mv = new ModelAndView("adicionar-usuario");
		Usuario usuario = new Usuario();
		mv.addObject("usuario", usuario);
		return mv;
	}

	@RequestMapping(method=RequestMethod.POST, name = "salvar")
	public ModelAndView salvar(@ModelAttribute("usuario") Usuario usuario) {
		System.out.format("Id: %s Nome: %s CPF: %s RG: %s DataNasc: %s Endereço %s", usuario.getIdUsuario(), usuario.getNome(), usuario.getCpf(), usuario.getRg(), usuario.getDataNasc(), usuario.getEndereco());
		usuarioRepository.save(usuario);
		ModelAndView mv = new ModelAndView("adicionar-usuario");
		mv.addObject("usuario", new Usuario());
		return mv;
	}

//	@RequestMapping(path = "/pesquisar-usuario")
//	public ModelAndView pesquisarUsuario() {
//		ModelAndView mv = new ModelAndView("pesquisar-usuario");
//		Iterable<Usuario> iter = usuarioRepository.findAll();
//		System.out.print(iter);
//		Usuario usuario = new Usuario();
//		mv.addObject("usuario", usuario);
//		mv.addObject("lista", iter);
//		return mv;
//	}

//	@RequestMapping(path = "/pesquisar-usuario", method = RequestMethod.POST, name = "pesquisar")
//	public ModelAndView pesquisar(@ModelAttribute("usuario") Usuario usuario) {
//		System.out.format("Id: %s", usuario.getIdUsuario());
//		
//		ModelAndView mv = new ModelAndView("pesquisar-usuario");
//		Optional<Usuario> opt = usuarioRepository.findById(usuario.getIdUsuario()) ;
//		System.out.print(opt);
//		usuario = new Usuario();
//		mv.addObject("usuario", usuario);
//		mv.addObject("lista", opt);
//		return mv;
//	}

}
