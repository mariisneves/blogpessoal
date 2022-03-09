package com.generation.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.generation.blogpessoal.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	//vai rodar esses métodos antes de começar os testes
	//deleteAll -> apaga tudo o que tinha antes (do teste anterior)
	//save -> salvar novos dados no banco em memória
	@BeforeAll 
	void start() {
		
		usuarioRepository.deleteAll(); //igual o delete sem where no mysql
		
		usuarioRepository.save(new Usuario (0L, "Mariana Neves", "mariana@email.com", "mari123456", "foto")); 
							   //aqui a gente não usa um objeto pronto, a gente cria na hora
		usuarioRepository.save(new Usuario (0L, "Gabriel Neves", "gabriel@email.com", "gab123456", "foto")); 
		usuarioRepository.save(new Usuario (0L, "Guilherme Neves", "guilherme@email.com", "gui123456", "foto")); 
		usuarioRepository.save(new Usuario (0L, "Kamila Muniz", "kamila@email.com", "kah123456", "foto"	)); 
		
	}
	
	@Test
	@DisplayName("Retorna 1 usuário") //nomes dos testes sempre objetivos
	public void deveRetornarUmUsuario() {
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("mariana@email.com"); //bota pra rodar
		assertTrue(usuario.get().getUsuario().equals("mariana@email.com")); //aqui faz o teste 
		//"é verdade que o usuário encontrado é mariana@email.com?
	}
	
	@Test
	@DisplayName("🥰 Retorna 3 usuários")
	public void deveRetornarTresUsuario() {
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("neves");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("Mariana Neves"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Gabriel Neves"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Guilherme Neves"));
	}

}
