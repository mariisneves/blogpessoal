package com.generation.blogpessoal.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity //faz com que a classe seja reconhecida como um banco de dados
@Table(name = "tb_postagens") //nomear tabela
public class Postagem {
	
	@Id //persistence
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	@NotBlank(message = "O campo título é obrigatório.") 
	@Size(min = 5, max = 100, message = "O campo deve ter entre 5 e 100 caracteres.") 
	private String titulo;
	
	@NotBlank(message = "O campo texto é obrigatório.") 
	@Size(min = 10, max = 1000, message = "O campo deve ter entre 10 e 1000 caracteres.") 
	private String texto;
	
	@UpdateTimestamp 
	private LocalDateTime data;
	
	// Relacionando postagens com a tabela tema
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;
	
	// Relacionando postagens com a tabela tema
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;

	
	// Métodos Get e Set
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public Tema getTema() {
		return this.tema;
	}
	public void setTema(Tema tema) {
		this.tema = tema;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
