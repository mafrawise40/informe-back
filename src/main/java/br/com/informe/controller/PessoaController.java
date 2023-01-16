package br.com.informe.controller;

import br.com.informe.dto.InformacaoPessoaDTO;
import br.com.informe.dto.PessoaDTO;
import br.com.informe.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/pessoa", produces = MediaType.APPLICATION_JSON_VALUE)
public class PessoaController {

    @Autowired
    PessoaService service;

    @PostMapping("/retornar-todos")
    public ResponseEntity<List<PessoaDTO>> getAll(){
        return ResponseEntity.ok().body(service.getlAll());
    }

    @DeleteMapping("/{id}")
    protected void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    @PostMapping()
    public ResponseEntity<PessoaDTO> inserir(@RequestBody PessoaDTO pessoaDTO){
        return ResponseEntity.ok().body(service.inserir(pessoaDTO));
    }

    @PutMapping
    public ResponseEntity<PessoaDTO> atualizar(@RequestBody PessoaDTO pessoadto){
        return ResponseEntity.ok().body(service.atualizar(pessoadto));
    }


    @PostMapping("/retornar-por-id")
    public ResponseEntity<PessoaDTO> getById(@RequestBody Long id){
        return ResponseEntity.ok().body(service.getById(id));
    }

    @PostMapping("/get-vinculos")
    public ResponseEntity<List<InformacaoPessoaDTO>> getVinculosPessoa(@RequestBody Long id){
        return ResponseEntity.ok().body(service.getVinculosPessoas(id));
    }

    @PostMapping("/get-by-paramentros")
    public ResponseEntity<List<PessoaDTO>> retornarPorParametros(@RequestBody PessoaDTO filtroPessoaDTO){
        return ResponseEntity.ok().body(service.getByParametros(filtroPessoaDTO));
    }


    @PostMapping(value = "/upload-file/{idPessoa}")
    public void getUploadFile(@RequestParam Map<String, MultipartFile> allRequestParams,
                              @RequestParam Map<String,String> comprimido ,
                              @RequestParam Map<String,String> tituloImagem
            , @PathVariable Long idPessoa) {
        service.uploadFotos(allRequestParams, comprimido,tituloImagem ,idPessoa );
    }

    @DeleteMapping("/{id}/{idImagem}")
    protected void deletarImagem(@PathVariable Long id , @PathVariable Long idImagem) {
        service.deletar(id , idImagem);
    }

}
