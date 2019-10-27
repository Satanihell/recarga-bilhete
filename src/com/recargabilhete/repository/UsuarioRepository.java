package com.recargabilhete.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.recargabilhete.entity.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository <Usuario, Long>{

}
