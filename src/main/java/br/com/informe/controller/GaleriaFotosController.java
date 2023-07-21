package br.com.informe.controller;

import br.com.informe.entity.Arquivo;
import br.com.informe.entity.ArquivoPessoa;
import br.com.informe.repository.ArquivoPessoaRepository;
import br.com.informe.repository.ArquivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController //API REST  { JSON }
@RequestMapping(value = "/galeria", produces = MediaType.APPLICATION_JSON_VALUE)
public class GaleriaFotosController {


    @Autowired
    ArquivoRepository arquivoRepository; // atributo da classe
    @Autowired
    ArquivoPessoaRepository arquivoPessoaRepository;

    @GetMapping(value = "/fotos")
    public List<Arquivo> retornarArquivos() {
        List<Arquivo> retorno = new ArrayList<>();
        retorno =  arquivoRepository.findAll(); //Select * from arquivo
        return retorno;
    }

    @GetMapping(value = "/retornarUmArquivo")
    public Arquivo retornarUmArquivo(@RequestParam Long id) {
        Optional<Arquivo> retorno;

        retorno = arquivoRepository.findById(id);

        if(retorno.isPresent()){ // se tiver valor dentro do optional
            return retorno.get();
        }
        return new Arquivo();
        //retorno = arquivoRepository.findById(id).orElseThrow( () -> new NoSuchElementException("Arquivo não encontrado"));//
        //return  retorno;
    }

    // /fotosPessoa
    @GetMapping(value = "/retornarFotosPessoa")
    public List<ArquivoPessoa> retornarFotosPessoa() {
        List<ArquivoPessoa> retorno = new ArrayList<>();
        retorno = arquivoPessoaRepository.findAll();
        return retorno;
    }

    //retornarUmArquivoPessoa
    @GetMapping(value = "/retornarUmArquivoPessoa")
    public ArquivoPessoa retornarUmArquivoPessoa(@RequestParam Long id) {

        Optional<ArquivoPessoa> retorno;

        retorno = arquivoPessoaRepository.findById(id);

        if(retorno.isPresent()){ // se tiver valor dentro do optional
            return retorno.get();
        }
        return new ArquivoPessoa();

    }
}
