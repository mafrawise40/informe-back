package br.com.informe.controller;

import br.com.informe.dto.ArquivoDTO;
import br.com.informe.service.ArquivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/arquivo", produces = MediaType.APPLICATION_JSON_VALUE)
public class ArquivoController {

        @Autowired
        ArquivoService service;

        @DeleteMapping("/{id}")
        protected void deletar(@PathVariable Long id) {
            service.deletar(id);
        }

        @PutMapping
        protected ResponseEntity<ArquivoDTO> acaoAtualizar(@RequestBody ArquivoDTO arquivoDTO) {
            return  ResponseEntity.ok().body(service.alterar(arquivoDTO));
        }
}
