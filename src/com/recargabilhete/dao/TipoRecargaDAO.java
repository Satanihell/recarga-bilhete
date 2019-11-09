package com.recargabilhete.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.recargabilhete.entity.TipoRecarga;

@Transactional
@Repository
public class TipoRecargaDAO {

	@PersistenceContext
	private EntityManager manager;

	public List<TipoRecarga> findAll() {
		return manager.createQuery("select t from tipoRecarga t", TipoRecarga.class).getResultList();
	}

	public TipoRecarga findById(Long idTipoRecarga) {
		return manager.find(TipoRecarga.class, idTipoRecarga);
	}

	public void salvar(TipoRecarga tipoRecarga) {
		manager.persist(tipoRecarga);
	}
	
}
