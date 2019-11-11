package com.recargabilhete.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.recargabilhete.entity.Usuario;
import com.recargabilhete.repository.UsuarioRepository;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@RequestMapping(path = "/adicionar-usuario", method=RequestMethod.GET)
	public ModelAndView adicionarUsuario() {
		ModelAndView mv = new ModelAndView("adicionar-usuario");
		Usuario usuario = new Usuario();
		mv.addObject("usuario", usuario);
		return mv;
	}

	@RequestMapping(path = "/adicionar-usuario", method=RequestMethod.POST, name = "salvar")
	public String salvar(@ModelAttribute("usuario") Usuario usuario) {
		System.out.format("Id: %s Nome: %s CPF: %s RG: %s DataNasc: %s Endereço %s", usuario.getIdUsuario(), usuario.getNome(), usuario.getCpf(), usuario.getRg(), usuario.getDataNasc(), usuario.getEndereco());
		usuarioRepository.save(usuario);
		ModelAndView mv = new ModelAndView("adicionar-usuario");
		mv.addObject("usuario", new Usuario());
		return "redirect:/adicionar-usuario";
	}

	@RequestMapping(path = "/pesquisar-usuario")
	public ModelAndView pesquisarUsuario() {
		ModelAndView mv = new ModelAndView("pesquisar-usuario");
		Iterable<Usuario> iter = usuarioRepository.findAll();
		System.out.print(iter);
		Usuario usuario = new Usuario();
		mv.addObject("usuario", usuario);
		mv.addObject("lista", iter);
		return mv;
	}

	@RequestMapping(path = "/pesquisar-usuario", method = RequestMethod.GET,  params = "idUsuario")
	public ModelAndView pesquisarUsuarioId(@ModelAttribute("usuario") Usuario usuarioDigitado, HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("pesquisar-usuario");
		long idUsuario = Long.parseLong(req.getParameter("idUsuario"));
		Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);
		System.out.print(usuario);
		mv.addObject("usuario", usuario);
		mv.addObject("lista", usuario);
		return mv;
		}
	
	@RequestMapping(path = "/pesquisar-usuario/remover/{idUsuario}", method = RequestMethod.POST, name = "remover")
	public String removerUsuario(@PathVariable(name = "idUsuario") String idUsuario){
		usuarioRepository.deleteById(Long.parseLong(idUsuario));
		return "redirect:/pesquisar-usuario";
	}
	
	@RequestMapping(path = "/pesquisar-usuario/alterar-usuario/{idUsuario}", method = RequestMethod.GET, name = "alterar")
	public ModelAndView alterarUsuario(@PathVariable(name = "idUsuario") String idUsuarioStr){
		ModelAndView mv = new ModelAndView("alterar-usuario");
		long idUsuario = Long.parseLong(idUsuarioStr);
		Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);
		mv.addObject("usuario", usuario);
		return mv;
	}
	
	@RequestMapping(path = "/pesquisar-usuario/alterar-usuario/{idUsuario}", method = RequestMethod.POST, name = "alterar")
	public String submitAlterarUsuario(@ModelAttribute(name = "usuario") Usuario usuario){
		usuarioRepository.save(usuario);
		return "redirect:/pesquisar-usuario";
	}

}