package br.com.gt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.gt.model.enumerator.VotoSessao;
import io.swagger.annotations.ApiModel;

@ApiModel(value = "Voto", description = "Representa uma voto")
@Table(
		name = "tb_voto"
)
@Entity
public class Voto {

	@Id
	@Column(name = "id_voto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idVoto;
	
	@Column(name = "id_pauta")
	private Integer idPauta;
	
	@Column(name = "id_associado")
	private Integer idAssociado;
	
	@Column(name="voto_sessao")
	@Enumerated(EnumType.STRING)
	private VotoSessao votoSessao;
	
	public Integer getIdVoto() {
		return idVoto;
	}
	public void setIdVoto(Integer idVoto) {
		this.idVoto = idVoto;
	}
	public Integer getIdPauta() {
		return idPauta;
	}
	public void setIdPauta(Integer idPauta) {
		this.idPauta = idPauta;
	}
	public Integer getIdAssociado() {
		return idAssociado;
	}
	public void setIdAssociado(Integer idAssociado) {
		this.idAssociado = idAssociado;
	}
	public VotoSessao getVotoSessao() {
		return votoSessao;
	}
	public void setVotoSessao(VotoSessao votoSessao) {
		this.votoSessao = votoSessao;
	}
}
