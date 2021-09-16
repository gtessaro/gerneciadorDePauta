package br.com.gt.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gt.exception.NotFoundException;
import br.com.gt.exception.UnauthorizedException;
import br.com.gt.model.Associado;
import br.com.gt.repository.AssociadoRepository;

@Service
public class AssociadoService {
	
	@Autowired
	private AssociadoRepository repository;
	
	public Associado getAssociadoByCpf(String cpf) {
		cpf = cpf.replace(".", "").replace("-", "");
		
		if(!isAssociadoHabilitadoVotacao(cpf)) {
			throw new UnauthorizedException("Associado não está permitido a votar nesta pauta.");
		}
		
		Associado associado;
		if(repository.existsByCpf(cpf)) {
			associado = (Associado) repository.findByCpf(cpf);
		}else {
			associado = new Associado();
			associado.setCpf(cpf);
			associado = repository.save(associado);
		}
		
		return associado;
	}
	
    public boolean isAssociadoHabilitadoVotacao(String cpf) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String validaAssociadoUrl = "https://user-info.herokuapp.com/users/{cpf}";
            ResponseEntity<String> response = restTemplate.getForEntity(validaAssociadoUrl, String.class, cpf);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode status = root.path("status");

            if ("ABLE_TO_VOTE".equals(status.textValue())) {
                return Boolean.TRUE;
            }

            return Boolean.FALSE;

        } catch (HttpClientErrorException | IOException ex) {
            throw new NotFoundException("Não foi possivel localizar o CPF do associado");
        }
    }
	
}
