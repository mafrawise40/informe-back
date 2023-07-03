package br.com.informe.controller;

import br.com.informe.client.BNMPClient;
import br.com.informe.dto.InformacaoPessoaDTO;
import br.com.informe.dto.PessoaDTO;
import br.com.informe.dto.cliente.BNMpContentDTO;
import br.com.informe.dto.cliente.BNMpRequestDTO;
import br.com.informe.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
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

    @Autowired
    BNMPClient bnmpClient;
    @PostMapping(value = "/importarPessoasBNMP")
    public void importarPessoasBNMP(){
        long idEstado = 7; //DF
        long idMunicipio = 1740; //São sebastião - DF
        BNMpRequestDTO dto = BNMpRequestDTO.builder().buscaOrgaoRecursivo(false).idEstado(idEstado).idMunicipio(idMunicipio).build();



        Map<String, String> headers = new HashMap<>();
        headers.put("authority", "portalbnmp.cnj.jus.br");
        headers.put("Cookie", "portalbnmp=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJndWVzdF9wb3J0YWxibm1wIiwiYXV0aCI6IlJPTEVfQU5PTllNT1VTIiwiZXhwIjoxNjc1Mjc1NzIzfQ.xQSkl14DhQy1YylurrlEwo0jOHNYjl7FpgCUSaZb6ECi7xpvl-L0kDiGoUn4zmdTQCWsW5z2peiTEenmXtTphw");
        headers.put("Content-Type", "application/json;charset=UTF-8");
        headers.put("Origin", "https://portalbnmp.cnj.jus.br");
        headers.put("Referer", "https://portalbnmp.cnj.jus.br/");
        headers.put("accept-language", "en-US,en;q=0.9");


        BNMpContentDTO retorno = bnmpClient.getPessoasMandado(0L , 20L ,dto , headers);


        for (Long i = 1L; i < retorno.getTotalPages(); i++) {
            retorno.getContent().addAll(bnmpClient.getPessoasMandado(i , 20L ,dto , headers).getContent());
        }

        retorno.getContent().forEach(mandado -> {
            StringBuilder textoObs = new StringBuilder(" Essa pessoa possui mandado de prisão em aberto. ");
            textoObs.append(" Mandado Nº Peça: " + mandado.getNumeroPeca());
            textoObs.append(" Mandado Nº Processo: " + mandado.getNumeroProcesso());
            textoObs.append(" Orgão: " + mandado.getNomeOrgao());
            textoObs.append(" <b> Data de Expedição Nº Peça: " + mandado.getDataExpedicao() + " </b>");

            PessoaDTO pessoaDTO = PessoaDTO.builder().nome(mandado.getNomePessoa())
                    .mandado("S")
                    .apelido(mandado.getAlcunha())
                    .mae(mandado.getNomeMae())
                    .pai(mandado.getNomePai())
                    .nascimento(mandado.getDataNascimentoFormatada())
                    .detalhe(textoObs.toString())
                    .build();

            if (pessoaDTO.getApelido().equals("Não informado")) {
                pessoaDTO.setApelido("");
            }
            if ( pessoaDTO.getPai().equals("Não informado")) {
                pessoaDTO.setPai("");
            }

            service.inserir(pessoaDTO);
        });
    }
}
