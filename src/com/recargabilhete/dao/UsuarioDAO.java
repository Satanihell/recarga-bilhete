package com.recargabilhete.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.recargabilhete.entity.Usuario;

@Transactional
@Repository
public class UsuarioDAO {

	@PersistenceContext
	private EntityManager manager;

	public List<Usuario> findAll() {
		return manager.createQuery("select u from usuario u", Usuario.class).getResultList();
	}

	public Usuario findById(Long idUsuario) {
		return manager.find(Usuario.class, idUsuario);
	}

	public void salvar(Usuario usuario) {
		manager.persist(usuario);
	}
	
}
