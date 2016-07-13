package br.com.taskmanager.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Tarefas")
public class Tarefa implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String descricao;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="diasDaSemana",joinColumns=@JoinColumn(name="id"))
	@Column(name="dia")
	private Set<String> diasDaSemana = new HashSet<>();
	
	private Integer duracao;

	@ManyToOne
	private Usuario usuario;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Set<String> getDiasDaSemana() {
		return diasDaSemana;
	}

	public void setDiasDaSemana(Set<String> diasDaSemana) {
		this.diasDaSemana = diasDaSemana;
	}

	public Integer getDuracao() {
		return duracao;
	}

	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}

	public void adicionarDia(String dia) {
		this.diasDaSemana.add(dia);
	}

	public void removerDia(String dia) {
		this.diasDaSemana.remove(dia);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
