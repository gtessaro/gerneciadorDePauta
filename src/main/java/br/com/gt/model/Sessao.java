package br.com.gt.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "Sessão", description = "Representa uma sessão")
@Table(
		name = "tb_sessao"
)
@Entity
public class Sessao {
	
	@Id
	@Column(name = "id_sessao")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSessao;
	
	@Column
	private Integer idPauta;
	
	@Column
	private Integer duracao;
	
	@Column(name="data_hora_inicio")
	private LocalDateTime DataHoraInicio;

	public Integer getIdSessao() {
		return idSessao;
	}

	public void setIdSessao(Integer idSessao) {
		this.idSessao = idSessao;
	}

	public Integer getDuracao() {
		return duracao;
	}

	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}

	public LocalDateTime getDataHoraInicio() {
		return DataHoraInicio;
	}

	public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
		DataHoraInicio = dataHoraInicio;
	}

	public Integer getIdPauta() {
		return idPauta;
	}

	public void setIdPauta(Integer idPauta) {
		this.idPauta = idPauta;
	}
	
}
