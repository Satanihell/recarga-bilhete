package com.recargabilhete.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.recargabilhete.entity.Cartao;

@Repository
public interface CartaoRepository extends CrudRepository <Cartao, Long> {

	
}
