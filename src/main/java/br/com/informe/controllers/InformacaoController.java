package br.com.informe.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/informacao", produces = MediaType.APPLICATION_JSON_VALUE)
public class InformacaoController {


    @GetMapping("/ok")
    public String informe(){
        return "ok";
    }
}
