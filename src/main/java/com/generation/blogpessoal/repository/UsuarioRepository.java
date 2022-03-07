package com.generation.blogpessoal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.blogpessoal.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public Optional<Usuario> findByUsuario(String usuario);
	
	public Optional<Usuario> findByNomeAndUsuario (String nome, String usuario);
	
	public Optional<Usuario> findByIdAndUsuario (Long id, String usuario);

}
