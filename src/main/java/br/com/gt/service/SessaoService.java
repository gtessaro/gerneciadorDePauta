package br.com.gt.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		// TODO fazer verificação se pauta existe
		Sessao sessao = new Sessao();
		sessao.setIdPauta(idPauta);
		sessao.setDuracao(duracao);
		sessao.setDataHoraInicio(LocalDateTime.now());
		
		repository.save(sessao);
		
		//TODO mover a resposabilidade de criar a thread 
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					
					Pauta pauta = pautaRepository.findById(idPauta).orElseThrow(null);
					pauta.setStatus(StatusPauta.ABERTA);
					pautaRepository.save(pauta);
					
					Thread.sleep(duracao * 60 * 1000);
					
					pauta.setStatus(StatusPauta.AGUARDANDO);
					pautaRepository.save(pauta);
					
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
			}
		};
		
		thread.start();
		
	}
	
}
