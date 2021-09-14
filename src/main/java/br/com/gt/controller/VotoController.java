package br.com.gt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gt.service.VotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Voto")
@RestController
@RequestMapping("/api/v1/voto")
public class VotoController {

	@Autowired
    private VotoService votoService;

    @ApiOperation("Registrar o voto de um associado a pauta, apenas é permitido realizar o voto em pautas que estão abertas.")
    @PutMapping
    public void realizarVoto(
			@ApiParam(value = "Código da pauta que será votada") @RequestParam Integer idPauta,
			@ApiParam(value = "Cpf do associado") @RequestParam String cpf,
			@ApiParam(value = "Voto do associado (SIM,NAO)") @RequestParam String voto
			) {
    		votoService.realizarVoto(idPauta, cpf,voto);
    }
	
}
