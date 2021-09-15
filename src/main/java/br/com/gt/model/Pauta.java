package br.com.gt.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
	private SituacaoPauta situacao;

	@Column(name="data_criacao")
	private LocalDateTime dataCriacao;
	
	@Column(name="data_encerramento")
	private LocalDateTime dataEncerramento;
	
	@Enumerated(EnumType.STRING)
	private StatusPauta status;
	
	@Column
	private Integer votosSim;
	@Column
	private Integer votosNao;
	
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
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public StatusPauta getStatus() {
		return status;
	}
	public void setStatus(StatusPauta status) {
		this.status = status;
	}
	public SituacaoPauta getSituacao() {
		return situacao;
	}
	public void setSituacao(SituacaoPauta situacao) {
		this.situacao = situacao;
	}
	public Integer getVotosSim() {
		return votosSim;
	}
	public void setVotosSim(Integer votosSim) {
		this.votosSim = votosSim;
	}
	public Integer getVotosNao() {
		return votosNao;
	}
	public void setVotosNao(Integer votosNao) {
		this.votosNao = votosNao;
	}
	public LocalDateTime getDataEncerramento() {
		return dataEncerramento;
	}
	public void setDataEncerramento(LocalDateTime dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}
	
}
