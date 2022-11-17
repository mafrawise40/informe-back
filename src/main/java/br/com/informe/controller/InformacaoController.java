package br.com.informe.controller;

import br.com.informe.dto.FiltroInformacaoDTO;
import br.com.informe.dto.InformacaoDTO;
import br.com.informe.service.InformacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/informacao", produces = MediaType.APPLICATION_JSON_VALUE)
public class InformacaoController {

    @Autowired
    InformacaoService service;

    @GetMapping("/retornar-todos")
    public ResponseEntity<List<InformacaoDTO>> getAll(){
      return ResponseEntity.ok().body(service.getlAll());
    }

    @PostMapping("/retornar-por-parametros")
    public ResponseEntity<List<InformacaoDTO>> retornarPorParametros(@RequestBody FiltroInformacaoDTO filtroInformacaoDTO){
        return ResponseEntity.ok().body(service.retornarPorParamentros(filtroInformacaoDTO));
    }

    @PostMapping()
    public ResponseEntity<InformacaoDTO> inserir(@RequestBody InformacaoDTO informacaoDTO){
        return ResponseEntity.ok().body(service.inserir(informacaoDTO));
    }

    @DeleteMapping("/{id}")
    protected void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    @GetMapping("/retornar-por-id/{id}")
    public ResponseEntity<InformacaoDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.getById(id));
    }
}
