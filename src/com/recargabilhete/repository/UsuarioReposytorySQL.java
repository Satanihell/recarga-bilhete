package com.recargabilhete.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.recargabilhete.entity.Usuario;

@Repository
public interface UsuarioReposytorySQL extends CrudRepository<Usuario, Long>{
    
	@Query("select \r\n" +
    		"  idUsuario,\r\n" + 
    		"  cpf,\r\n" + 
    		"  dataNasc,\r\n" + 
    		"  endereco,\r\n" + 
    		"  nome,\r\n" + 
    		"  rg" +
    		" from Usuario usuario ")
    public Iterable<Usuario> findAll();
    	
	@Query("    select\r\n" + 
			"        idUsuario as idUsuari1_3_0_,\r\n" + 
			"        cpf as cpf2_3_0_,\r\n" + 
			"        dataNasc as dataNasc3_3_0_,\r\n" + 
			"        endereco as endereco4_3_0_,\r\n" + 
			"        nome as nome5_3_0_,\r\n" + 
			"        rg as rg6_3_0_ \r\n" + 
			"    from\r\n" + 
			"        Usuario \r\n" + 
			"    where\r\n" + 
			"        idUsuario= :idUsuario")
    public Optional<Usuario> findById(@Param("idUsuario") Usuario idUsuario);
	
	@Procedure(procedureName = "consulta_usuario")
	@Transactional
    public Optional<Usuario> findByIdProcedure(@Param("idUsuarioRecebido") long idUsuario);
    
}