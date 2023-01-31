package br.com.informe.controller;

import br.com.informe.dto.FiltroInformacaoDTO;
import br.com.informe.dto.InformacaoDTO;
import br.com.informe.service.InformacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/informacao", produces = MediaType.APPLICATION_JSON_VALUE)
public class InformacaoController {

    @Autowired
    InformacaoService service;

    @PostMapping("/retornar-todos")
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

    @DeleteMapping("/{id}/{idImagem}")
    protected void deletarImagem(@PathVariable Long id , @PathVariable Long idImagem) {
        service.deletar(id , idImagem);
    }

    @PostMapping("/retornar-por-id")
    public ResponseEntity<InformacaoDTO> getById(@RequestBody Long id){
        return ResponseEntity.ok().body(service.getById(id));
    }

    @PostMapping("/retornar-por-idd/{id}")
    public ResponseEntity<InformacaoDTO> getByIdd(@PathVariable Long id){
        return ResponseEntity.ok().body(service.getById(id));
    }

    @PutMapping
    protected ResponseEntity<InformacaoDTO> acaoAtualizar(@RequestBody InformacaoDTO informacaoDTO) {

        return  ResponseEntity.ok().body(service.update(informacaoDTO));
    }

    @PostMapping(value = "/upload-file/{idInformacao}")
    public void getUploadFile(@RequestParam Map<String,MultipartFile> allRequestParams,
                              @RequestParam Map<String,String> comprimido ,
                              @RequestParam Map<String,String> tituloImagem
            , @PathVariable Long idInformacao) {
        service.uploadFotos(allRequestParams, comprimido,tituloImagem ,idInformacao );
    }


    @PostMapping("/get-informacao-relatorio")
    public ResponseEntity<List<InformacaoDTO>> getInformacaoRelatorio(@RequestBody FiltroInformacaoDTO filtroInformacaoDTO){
        return ResponseEntity.ok().body(service.getInformacaoRelatorio(filtroInformacaoDTO));
    }

}
