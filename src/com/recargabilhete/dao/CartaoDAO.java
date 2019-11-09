package com.recargabilhete.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.recargabilhete.entity.Cartao;


@Transactional
@Repository
public class CartaoDAO {

	@PersistenceContext
	private EntityManager manager;

	public List<Cartao> findAll() {
		return manager.createQuery("select c from cartao c", Cartao.class).getResultList();
	}

	public Cartao findById(Long idCartao) {
		return manager.find(Cartao.class, idCartao);
	}

	public void salvar(Cartao cartao) {
		manager.persist(cartao);
	}

}
