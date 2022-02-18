package com.generation.blogpessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.blogpessoal.model.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository <Postagem, Long> {
	
	List <Postagem> findAllByTituloContainingIgnoreCase(String titulo);
	//select * from tb_postagens where titulo like "%titulo%"
	//find = select | * = All | from = By | nome do atributo = Titulo
	//like = Containing | ignorar diferente de cx alta e baixa = IgnoreCase
	//selecionando atributo como parâmetro = (String titulo)


}