package br.com.gt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gt.model.Voto;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Integer>{
	Voto findByIdPautaAndIdAssociado(Integer idPauta, Integer idAssociado);
}
