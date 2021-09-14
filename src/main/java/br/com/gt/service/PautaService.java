package br.com.gt.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gt.exception.GenericException;
import br.com.gt.exception.NotFoundException;
import br.com.gt.model.Pauta;
import br.com.gt.model.dto.PautaDto;
import br.com.gt.model.enumerator.SituacaoPauta;
import br.com.gt.model.enumerator.StatusPauta;
import br.com.gt.model.enumerator.VotoSessao;
import br.com.gt.repository.PautaRepository;

@Service
public class PautaService {
	
	@Autowired
	private PautaRepository repository;
	
	@Autowired
	private VotoService votoService;
	
	public Pauta novaPauta(PautaDto pautaDto) {
		Pauta pauta = new Pauta();
		
		pauta.setDescricao(pautaDto.getDescricao());
		pauta.setTitulo(pautaDto.getTitulo());
		pauta.setDataCriacao(LocalDate.now());
		pauta.setStatus(StatusPauta.AGUARDANDO);
		
		return repository.save(pauta);
	}
	
	public void concluirPauta(Integer idPauta) {
		Pauta pauta = repository.getById(idPauta);
		pauta.setStatus(StatusPauta.ENCERRADA);
		pauta.setVotosSim(votoService.getVotosByPauta(idPauta, VotoSessao.SIM));
		pauta.setVotosNao(votoService.getVotosByPauta(idPauta, VotoSessao.NAO));
		pauta.setSituacao( pauta.getVotosSim()>pauta.getVotosNao()?SituacaoPauta.APROVADA:SituacaoPauta.NAO_APROVADA);
		
		repository.save(pauta);
	}
	
	public void cancelarPauta(Integer idPauta) {
		Pauta pauta = repository.getById(idPauta);
		pauta.setStatus(StatusPauta.CANCELADA);
		
		repository.save(pauta);
	}
	
	public List<Pauta> listarPautas(String status){
		
		if(status != null && !"TODAS".equalsIgnoreCase(status))
			return repository.findByStatus(StatusPauta.valueOf(status));
		return repository.findAll();
	}
	
	public Boolean permiteVotar(Integer idPauta){
		if(!repository.existsById(idPauta)) {
			throw new NotFoundException("Pauta não localizada para o id " + idPauta);
		}
		Pauta pauta = repository.findById(idPauta).orElse(new Pauta());
		
		if(!StatusPauta.ABERTA.equals(pauta.getStatus())) {
			throw new GenericException("Não existe nenhuma sessão aberta para votação no momento.");
		}
		
		return true;
	}
	
}
