package br.com.gt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gt.service.SessaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Sessão")
@RestController
@RequestMapping("/api/v1/sessao")
public class SessaoController {

	@Autowired
    private SessaoService sessaoService;

    @ApiOperation("Abrir uma sessao.")
    @PutMapping
    public void criarSessao(
			@ApiParam(value = "Código da pauta que será aberta uma nova sessão") 
			@RequestParam Integer idPauta,
			@ApiParam(value = "Duração da sessão em minutos") 
			@RequestParam(required = false) Integer duracaoMinutos
			) {
    	sessaoService.criarSessao(idPauta, duracaoMinutos);
    }
	
}
