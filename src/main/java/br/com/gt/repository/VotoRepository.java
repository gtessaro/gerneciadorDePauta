package br.com.gt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gt.model.Voto;
import br.com.gt.model.enumerator.VotoSessao;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Integer>{
	public Voto findByIdPautaAndIdAssociado(Integer idPauta, Integer idAssociado);
	public Boolean existsByIdPautaAndIdAssociado(Integer idPauta, Integer idAssociado);
	public Integer countByIdPautaAndVotoSessao(Integer idPauta, VotoSessao votoSessao);
}
