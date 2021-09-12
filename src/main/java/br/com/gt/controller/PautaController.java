package br.com.gt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gt.model.Pauta;
import br.com.gt.model.dto.PautaDto;
import br.com.gt.service.PautaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Pauta")
@RestController
@RequestMapping("/api/v1/pauta")
public class PautaController {

	@Autowired
    private PautaService pautaService;

    @ApiOperation("Criar uma nova pauta")
    @PutMapping
    public void criarPauta(
			@ApiParam(name = "corpo", value = "Representação de uma nova pauta") 
			@Validated @RequestBody PautaDto pauta) {
    	pautaService.novaPauta(pauta);
    }
    
    @ApiOperation("Concluir uma pauta, e encerrar as votações")
    @PostMapping("/concluir/{idPauta}")
    public void encerrarPauta(@PathVariable Integer idPauta) {
    	pautaService.concluirPauta(idPauta);
    }
    
    @ApiOperation("Cancelar uma pauta, após cancelada, não poderá mais ser alterada.")
    @PostMapping("/cancelar/{idPauta}")
    public void cancelarPauta(@PathVariable Integer idPauta) {
    	pautaService.cancelarPauta(idPauta);
    }
    
    @ApiOperation("Listar todas as pautas, por padrão, apenas as pautas ABERTAS são listadas")
    @GetMapping
    public List<Pauta> listarPautas(@RequestParam(required = false) String status) {
    	return pautaService.listarPautas(status);
    }
    
	
}
