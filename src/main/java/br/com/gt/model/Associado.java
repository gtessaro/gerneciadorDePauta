package br.com.gt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(
		name = "tb_associado"
)
@Entity
public class Associado {
	
	@Id
	@Column(name = "id_associado")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAssociado;
	
	@Column
	private String cpf;
	
	public Integer getIdAssociado() {
		return idAssociado;
	}
	public void setIdAssociado(Integer idAssociado) {
		this.idAssociado = idAssociado;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
