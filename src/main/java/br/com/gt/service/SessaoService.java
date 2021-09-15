package br.com.gt.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gt.exception.GenericException;
import br.com.gt.exception.NotFoundException;
import br.com.gt.model.Pauta;
import br.com.gt.model.Sessao;
import br.com.gt.model.enumerator.StatusPauta;
import br.com.gt.repository.PautaRepository;
import br.com.gt.repository.SessaoRepository;

@Service
public class SessaoService {
	
	@Autowired
	private SessaoRepository repository;
	
	@Autowired
	private PautaRepository pautaRepository;
	
	public void criarSessao(Integer idPauta, Integer duracao) {
		if(!pautaRepository.existsById(idPauta)) {
			throw new NotFoundException("Pauta não localizada para o id " + idPauta);
		}
		
		Pauta pauta = pautaRepository.findById(idPauta).orElseThrow(null);
		if(!StatusPauta.AGUARDANDO.equals(pauta.getStatus())) {
			throw new GenericException("Não é possivel abrir uma sessão para uma pauta com status "+pauta.getStatus().toString());
		}
		
		if(duracao == null || duracao<1) {
			duracao = Integer.valueOf(1);
		}
		
		Sessao sessao = new Sessao();
		sessao.setIdPauta(idPauta);
		sessao.setDuracao(duracao);
		sessao.setDataHoraInicio(LocalDateTime.now());
		
		repository.save(sessao);
		
		final Integer duracaoFinal = duracao;
		
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					
					Pauta pauta = pautaRepository.findById(idPauta).orElseThrow(()->new NotFoundException("Pauta não localizada para o id " + idPauta));
					pauta.setStatus(StatusPauta.ABERTA);
					pautaRepository.save(pauta);
					
					Thread.sleep(duracaoFinal * 60 * 1000L);
					
					pauta.setStatus(StatusPauta.AGUARDANDO);
					pautaRepository.save(pauta);
					
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	                Thread.currentThread().interrupt();
	            }
			}
		};
		thread.start();
		
	}
	
}
