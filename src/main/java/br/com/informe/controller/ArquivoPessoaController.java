package br.com.informe.controller;

import br.com.informe.dto.ArquivoPessoaDTO;
import br.com.informe.service.ArquivoPessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/arquivoPessoa", produces = MediaType.APPLICATION_JSON_VALUE)
public class ArquivoPessoaController {


    @Autowired
    ArquivoPessoaService service;

    @DeleteMapping("/{id}")
    protected void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    @PutMapping
    protected ResponseEntity<ArquivoPessoaDTO> acaoAtualizar(@RequestBody ArquivoPessoaDTO arquivoDTO) {
        return  ResponseEntity.ok().body(service.alterar(arquivoDTO));
    }
}
