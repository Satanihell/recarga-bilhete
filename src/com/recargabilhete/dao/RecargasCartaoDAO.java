package com.recargabilhete.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.recargabilhete.entity.RecargasCartao;

@Transactional
@Repository
public class RecargasCartaoDAO {
	
	@PersistenceContext
	private EntityManager manager;

	public List<RecargasCartao> findAll() {
		return manager.createQuery("select r from recargascartao r", RecargasCartao.class).getResultList();
	}

	public RecargasCartao findById(Long idRecargasCartao) {
		return manager.find(RecargasCartao.class, idRecargasCartao);
	}

	public void salvar(RecargasCartao recargasCartao) {
		manager.persist(recargasCartao);
	}

}
