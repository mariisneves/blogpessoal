package com.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*") // colocar essa pro front poder se comunicar com o back
public class PostagemController {

	@Autowired
	private PostagemRepository postagemRepository;

	@GetMapping
	public ResponseEntity<List<Postagem>> getAll() {
		return ResponseEntity.ok(postagemRepository.findAll());
		// esse método nunca vai dar erro pq ele sempre traz tudo
		// mesmo se não houver nenhuma postagem, nesse caso ele devolve o vazio

		// equivalente à: select * from tb_postagens;
	}

	@GetMapping("/{id}") // entende que o que tá dentro das chaves não é um caminho e sim uma variável
	public ResponseEntity<Postagem> getById(@PathVariable Long id) {
		return postagemRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta)) // resposta positiva do IF
				.orElse(ResponseEntity.notFound().build()); // resposta negativa do IF
		// optional map -> mapeia a resposta de um determinado método
		// Lambda = (resposta -> oqueretorna)
		// map/orElse = funções do Optional
	}

	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));

	}

	@PostMapping // pode usar o mesmo caminho desde que o verbo seja outro
	public ResponseEntity<Postagem> postPostagem(@Valid @RequestBody Postagem postagem) {
		// classe REsponseEntity | recebe Postagem | objeto do tipo Postagem
		// precisa informar pro Spring onde ele encontra esse objeto -> @RequestBody
		// pra validar se o objeto está dentro das regras estabelecidas em model ->
		// @Valid
		return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));
		// chama o status created (201) e salva no corpo do status
	}

	//DA AULA
//	 @PutMapping
//	 public ResponseEntity<Postagem> putPostagem(@Valid @RequestBody Postagem postagem){ 
//		 return ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagem));
//	  //através do ID que ele sabe que você vai atualizar e não criar um novo recurso
//	 }
	 

	@PutMapping
	public ResponseEntity<Postagem> putPostagem(@Valid @RequestBody Postagem postagem) {
		return postagemRepository.findById(postagem.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagem)))
				.orElse(ResponseEntity.notFound().build());
	}

	//DA AULA
//	@DeleteMapping("/{id}")
//	public void deletePostagem(@PathVariable Long id) {
//		postagemRepository.deleteById(id);
//	
//	}
	
	//COM VOID
	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletePostagem(@PathVariable Long id) {
		Optional<Postagem> postagem = postagemRepository.findById(id);
		
		if(postagem.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	
		postagemRepository.deleteById(id);
		
	}
	
	//SEM VOID
//	@DeleteMapping("/{id}")
//	public ResponseEntity<Object> deletePostagem(@PathVariable Long id) {
//		return postagemRepository.findById(id).map(resposta -> {
//			postagemRepository.deleteById(id); return ResponseEntity.noContent().build();
//		}) .orElse(ResponseEntity.notFound().build());
//	}


}
