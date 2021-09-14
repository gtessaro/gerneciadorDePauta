package br.com.gt.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.gt.model.enumerator.SituacaoPauta;
import br.com.gt.model.enumerator.StatusPauta;
import io.swagger.annotations.ApiModel;

@ApiModel(value = "Pauta", description = "Representa uma pauta")
@Table(
		name = "tb_pauta"
)
@Entity
public class Pauta {
	
	@Id
	@Column(name = "id_pauta")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPauta;
	
	@Column
	private String titulo;
	
	@Column
	private String descricao;

	@Column
	private SituacaoPauta Situacao;

	@Column(name="data_criacao")
	private LocalDate dataCriacao;
	
	@Enumerated(EnumType.STRING)
	private StatusPauta status;
	
	public Integer getIdPauta() {
		return idPauta;
	}
	public void setIdPauta(Integer idPauta) {
		this.idPauta = idPauta;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public LocalDate getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public StatusPauta getStatus() {
		return status;
	}
	public void setStatus(StatusPauta status) {
		this.status = status;
	}
	public SituacaoPauta getSituacao() {
		return Situacao;
	}
	public void setSituacao(SituacaoPauta situacao) {
		Situacao = situacao;
	}
	
}
