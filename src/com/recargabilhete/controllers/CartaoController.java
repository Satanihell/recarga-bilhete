package com.recargabilhete.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.recargabilhete.entity.Cartao;
import com.recargabilhete.entity.Usuario;
import com.recargabilhete.repository.CartaoRepository;
import com.recargabilhete.repository.UsuarioRepository;

@Controller
public class CartaoController {

	@Autowired
	private CartaoRepository cartaoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@RequestMapping(path = "/adicionar-cartao", method = RequestMethod.GET)
	public ModelAndView cartaoAdicionar() {
		ModelAndView mv = new ModelAndView("adicionar-cartao");
		Cartao cartao = new Cartao();
		mv.addObject("cartao", cartao);
		return mv;
	}

	@RequestMapping(path = "/adicionar-cartao", method = RequestMethod.POST, name = "salvar")
	public ModelAndView cartaoSalvar(@ModelAttribute("cartao") Cartao cartao, @RequestParam("idUsuario") long idUsuario) {
		Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);
		cartao.setUsuario(usuario);
		cartao.setStatusCartao("Ativo");

		cartaoRepository.save(cartao);
		ModelAndView mv = new ModelAndView("adicionar-cartao");
		mv.addObject("cartao", new Cartao());
		return mv;
	}

	@RequestMapping(path = "/pesquisar-cartao", method = RequestMethod.GET)
	public ModelAndView cartaoPesquisar() {
		ModelAndView mv = new ModelAndView("pesquisar-cartao");
		Iterable<Cartao> iter = cartaoRepository.findAll();
		Cartao cartao = new Cartao();
		mv.addObject("cartao", cartao);
		mv.addObject("lista", iter);
		return mv;
	}

	@RequestMapping(path = "/pesquisar-cartao", method = RequestMethod.GET, params = "idCartao")
	public ModelAndView pesquisarCartaoId(@ModelAttribute("cartao") Cartao cartaoDigitado) {
		ModelAndView mv = new ModelAndView("pesquisar-cartao");
		Cartao cartao = cartaoRepository.findById(cartaoDigitado.getIdCartao()).orElse(null);
		System.out.print(cartao);
		mv.addObject("cartao", cartao);
		mv.addObject("lista", cartao);
		return mv;
	}

	@RequestMapping(path = "/pesquisar-cartao/remover/{idCartao}", method = RequestMethod.POST, name = "remover")
	public String removerCartao(@PathVariable(name = "idCartao") String idCartao) {
		cartaoRepository.deleteById(Long.parseLong(idCartao));
		return "redirect:/pesquisar-cartao";
	}

	@RequestMapping(path = "/pesquisar-cartao/alterar-cartao/{idCartao}", method = RequestMethod.GET, name = "alterar")
	public ModelAndView alterarCartao(@PathVariable(name = "idCartao") String idCartaoStr) {
		ModelAndView mv = new ModelAndView("alterar-cartao");
		long idCartao = Long.parseLong(idCartaoStr);
		Cartao cartao = cartaoRepository.findById(idCartao).orElse(null);
		mv.addObject("cartao", cartao);
		return mv;
	}

	@RequestMapping(path = "/pesquisar-cartao/alterar-cartao/{idCartao}", method = RequestMethod.POST, name = "alterar")
	public String submitAlterarCartao(@ModelAttribute(name = "cartao") Cartao cartao, @RequestParam("idUsuario") long idUsuario){
		Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);
		cartao.setUsuario(usuario);
		cartao.setStatusCartao("Ativo");
		
		
		cartaoRepository.save(cartao);
		return "redirect:/pesquisar-cartao";
	}

}
