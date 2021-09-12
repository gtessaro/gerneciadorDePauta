package br.com.gt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gt.model.Sessao;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Integer>{

}
