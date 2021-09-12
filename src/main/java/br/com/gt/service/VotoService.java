package br.com.gt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gt.exception.GenericException;
import br.com.gt.exception.NotFoundException;
import br.com.gt.model.Associado;
import br.com.gt.model.Voto;
import br.com.gt.model.enumerator.VotoSessao;
import br.com.gt.repository.AssociadoRepository;
import br.com.gt.repository.VotoRepository;

@Service
public class VotoService {
	
	@Autowired
	private VotoRepository repository;
	@Autowired
	private PautaService pautaService;
	@Autowired
	private AssociadoRepository associadoRepository;
	
	public void realizarVoto(Integer idPauta, String cpf, String opcaoVoto) throws NotFoundException{
		// TODO Validar Cpf
		
		if(!pautaService.permiteVotar(idPauta)) {
			throw new GenericException("Erro ao realizar voto.");
		}
		Associado associado;
		if(associadoRepository.existsByCpf(cpf)) {
			associado = (Associado) associadoRepository.findByCpf(cpf);
		}else {
			associado = new Associado();
			associado.setCpf(cpf);
			associado = associadoRepository.save(associado);
		}
		
		Voto voto = (Voto) repository.findByIdPautaAndIdAssociado(idPauta, associado.getIdAssociado());
		if(voto == null) {
			voto = new Voto();
			voto.setIdAssociado(associado.getIdAssociado());
			voto.setIdPauta(idPauta);
		}
		voto.setVotoSessao(VotoSessao.valueOf(opcaoVoto));
		repository.save(voto);
		
	}
	
}
