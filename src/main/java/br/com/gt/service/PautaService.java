package br.com.gt.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gt.model.Pauta;
import br.com.gt.model.dto.PautaDto;
import br.com.gt.model.enumerator.StatusPauta;
import br.com.gt.repository.PautaRepository;

@Service
public class PautaService {
	
	@Autowired
	private PautaRepository repository;
	
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
		
		// TODO Calcular os votos, e retornar os dados da votação
		
		repository.save(pauta);
	}
	
	public void cancelarPauta(Integer idPauta) {
		Pauta pauta = repository.getById(idPauta);
		pauta.setStatus(StatusPauta.CANCELADA);
		
		repository.save(pauta);
	}
	
	public List<Pauta> listarPautas(String status){
//		if(status == null) {
//			status = StatusPauta.ABERTA.toString();
//		}
		
		if(status != null && !"TODAS".equalsIgnoreCase(status))
			return repository.findByStatus(StatusPauta.valueOf(status));
		return repository.findAll();
	}
	
}
