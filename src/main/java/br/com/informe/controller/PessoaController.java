package br.com.informe.controller;

import br.com.informe.dto.PessoaDTO;
import br.com.informe.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/retornar-por-id")
    public ResponseEntity<PessoaDTO> getById(@RequestBody Long id){
        return ResponseEntity.ok().body(service.getById(id));
    }

   /* @PostMapping("/retornar-por-parametros")
    public ResponseEntity<List<PessoaDTO>> retornarPorParametros(@RequestBody FiltroPessoaDTO filtroPessoaDTO){
        return ResponseEntity.ok().body(service.retornarPorParamentros(filtroPessoaDTO));
    }

    @DeleteMapping("/{id}/{idImagem}")
    protected void deletarImagem(@PathVariable Long id , @PathVariable Long idImagem) {
        service.deletar(id , idImagem);
    }

    @PostMapping("/retornar-por-id")
    public ResponseEntity<PessoaDTO> getById(@RequestBody Long id){
        return ResponseEntity.ok().body(service.getById(id));
    }

    @PutMapping
    protected ResponseEntity<PessoaDTO> acaoAtualizar(@RequestBody PessoaDTO PessoaDTO) {

        return  ResponseEntity.ok().body(service.update(PessoaDTO));
    }

    @PostMapping(value = "/upload-file/{idInformacao}")
    public void getUploadFile(@RequestParam Map<String, MultipartFile> allRequestParams,
                              @RequestParam Map<String,String> comprimido ,
                              @RequestParam Map<String,String> tituloImagem
            , @PathVariable Long idInformacao) {
        service.uploadFotos(allRequestParams, comprimido,tituloImagem ,idInformacao );
    }*/
}
