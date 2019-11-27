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
import com.recargabilhete.entity.RecargasCartao;
import com.recargabilhete.entity.TipoRecarga;
import com.recargabilhete.repository.CartaoRepository;
import com.recargabilhete.repository.RecargasCartaoRepository;
import com.recargabilhete.repository.TipoRecargaRepository;

@Controller
public class RecargasCartaoController {
	
	@Autowired
	private RecargasCartaoRepository recargasCartaoRepository;
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private TipoRecargaRepository tipoRecargaRepository;
	
	@RequestMapping(path = "/adicionar-recargas-cartao", method = RequestMethod.GET)
	public ModelAndView recargasCartao() { 
		ModelAndView mv = new ModelAndView("adicionar-recargas-cartao");
		Iterable<RecargasCartao> iter = recargasCartaoRepository.findAll();
		RecargasCartao recargasCartao = new RecargasCartao();
		mv.addObject("recargasCartao", recargasCartao);
		mv.addObject("lista", iter);
		return mv;
	}
	
	@RequestMapping(path = "/adicionar-recargas-cartao", method = RequestMethod.POST, name = "salvar")
	public ModelAndView RecargasCartaoSalvar(@ModelAttribute("recargasCartao") RecargasCartao recargasCartao, @RequestParam("idCartao") long idCartao, @RequestParam("idTipoRecarga") long idTipoRecarga) {
		
		Cartao cartao = cartaoRepository.findById(idCartao).orElse(null);
		recargasCartao.setCartao(cartao);
		
		TipoRecarga tipoRecarga = tipoRecargaRepository.findById(idTipoRecarga).orElse(null);
		recargasCartao.setTipoRecarga(tipoRecarga);
		
		recargasCartaoRepository.save(recargasCartao);
		ModelAndView mv = new ModelAndView("adicionar-recargas-cartao");
		mv.addObject("recargasCartao", new RecargasCartao());
		return mv;
	}
	
	@RequestMapping(path = "/pesquisar-recargas-cartao", method = RequestMethod.GET)
	public ModelAndView recargasCartaoPesquisar() {
		ModelAndView mv = new ModelAndView("pesquisar-recargas-cartao");
		Iterable<RecargasCartao> iter = recargasCartaoRepository.findAll();
		Cartao recargasCartao = new Cartao();
		mv.addObject("recargasCartao", recargasCartao);
		mv.addObject("lista", iter);
		return mv;
	}

	@RequestMapping(path = "/pesquisar-recargas-cartao", method = RequestMethod.GET, params = "idRecargaCartao")
	public ModelAndView pesquisarRecargaCartaoId(@ModelAttribute("recargasCartao") Cartao recargasCartaoDigitado, @RequestParam("idRecargaCartao") long idRecargaCartao) {
		ModelAndView mv = new ModelAndView("pesquisar-recargas-cartao");
		RecargasCartao recargasCartao = recargasCartaoRepository.findById(idRecargaCartao).orElse(null);
		mv.addObject("recargasCartao", recargasCartao);
		mv.addObject("lista", recargasCartao);
		return mv;
	}

	@RequestMapping(path = "/pesquisar-recargas-cartao/remover/{idRecargaCartao}", method = RequestMethod.POST, name = "remover")
	public String removerRecargaCartao(@PathVariable(name = "idRecargaCartao") String idRecargaCartao) {
		recargasCartaoRepository.deleteById(Long.parseLong(idRecargaCartao));
		return "redirect:/pesquisar-recargas-cartao";
	}

	@RequestMapping(path = "/pesquisar-recargas-cartao/alterar-recargas-cartao/{idRecargaCartao}", method = RequestMethod.GET, name = "alterar")
	public ModelAndView alterarCartao(@PathVariable(name = "idRecargaCartao") String idRecargaCartaoStr) {
		ModelAndView mv = new ModelAndView("alterar-recargas-cartao");
		long idRecargaCartao = Long.parseLong(idRecargaCartaoStr);
		RecargasCartao recargasCartao = recargasCartaoRepository.findById(idRecargaCartao).orElse(null);
		mv.addObject("recargasCartao", recargasCartao);
		System.out.print(recargasCartao);
		return mv;
	}

	@RequestMapping(path = "/pesquisar-recargas-cartao/alterar-recargas-cartao/{idRecargaCartao}", method = RequestMethod.POST, name = "alterar")
	public String submitAlterarRecargaCartao(@ModelAttribute(name = "recargasCartao") RecargasCartao recargasCartao, 
			@RequestParam("idTipoRecarga") long idTipoRecarga, @RequestParam("idCartao") long idCartao) {
		
		System.out.print(recargasCartao);
		
		TipoRecarga tipoRecargas = tipoRecargaRepository.findById(idTipoRecarga).orElse(null);
		recargasCartao.setTipoRecarga(tipoRecargas);
		
		Cartao cartao = cartaoRepository.findById(idCartao).orElse(null);
		recargasCartao.setCartao(cartao);

		recargasCartaoRepository.save(recargasCartao);
		return "redirect:/pesquisar-recargas-cartao";
	}


}