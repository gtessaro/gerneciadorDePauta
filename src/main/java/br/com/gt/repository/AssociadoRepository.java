package br.com.gt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gt.model.Associado;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Integer>{
	public Associado findByCpf(String cpf);
	public Boolean existsByCpf(String cpf);
}
