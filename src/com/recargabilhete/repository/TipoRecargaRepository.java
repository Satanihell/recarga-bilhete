package com.recargabilhete.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.recargabilhete.entity.TipoRecarga;

@Repository
public interface TipoRecargaRepository extends CrudRepository <TipoRecarga, Long>{


}