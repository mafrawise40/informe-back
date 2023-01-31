package br.com.informe.controller;


import br.com.informe.dto.VeiculoDTO;
import br.com.informe.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/veiculo", produces = MediaType.APPLICATION_JSON_VALUE)
public class VeiculoController {

    @Autowired
    VeiculoService vService;

    @PostMapping("/retornar-todos-carater-geral")
    public ResponseEntity<List<VeiculoDTO>> getAllCaraterGeral(){
        return ResponseEntity.ok().body(vService.getAllCaraterGeral());
    }

    @PostMapping("/retornar-todos")
    public ResponseEntity<List<VeiculoDTO>> getAll(){
        return ResponseEntity.ok().body(vService.getAll());
    }

    @PostMapping("/incluir-carater-geral")
    public ResponseEntity<VeiculoDTO> incluirCaraterGeral(@RequestBody Long id){
        return ResponseEntity.ok().body(vService.incluirCaraterGeral(id));
    }

    @PostMapping("/incluir-desfecho")
    public ResponseEntity<VeiculoDTO> incluirDesfecho(@RequestBody VeiculoDTO dto){
        return ResponseEntity.ok().body(vService.incluirDesfechoCaraterGeral(dto));
    }

    @PostMapping()
    public ResponseEntity<VeiculoDTO> inserir(@RequestBody VeiculoDTO veiculoDTO){
        return ResponseEntity.ok().body(vService.inserir(veiculoDTO));
    }


    @PutMapping
    public ResponseEntity<VeiculoDTO> atualizar(@RequestBody VeiculoDTO veiculoDTO){
        return ResponseEntity.ok().body(vService.atualizar(veiculoDTO));
    }

    @PostMapping("/retornar-por-id")
    public ResponseEntity<VeiculoDTO> getById(@RequestBody Long id){
        return ResponseEntity.ok().body(vService.getById(id));
    }


}
