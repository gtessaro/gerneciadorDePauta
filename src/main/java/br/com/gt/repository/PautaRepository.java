package br.com.gt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gt.model.Pauta;
import br.com.gt.model.enumerator.StatusPauta;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Integer>{
	public List<Pauta> findByStatus(StatusPauta status);
}
