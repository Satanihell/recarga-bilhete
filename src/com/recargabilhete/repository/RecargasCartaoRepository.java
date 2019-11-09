package com.recargabilhete.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.recargabilhete.entity.RecargasCartao;;

@Repository
public interface RecargasCartaoRepository extends CrudRepository <RecargasCartao, Long>{

}
