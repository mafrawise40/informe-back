package br.com.informe.client;

import br.com.informe.dto.cliente.BNMpContentDTO;
import br.com.informe.dto.cliente.BNMpRequestDTO;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Headers({"Accept-Language: en-US,en;q=0.9" ,
        "authority:portalbnmp.cnj.jus.br" ,
        "Cookie:portalbnmp=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJndWVzdF9wb3J0YWxibm1wIiwiYXV0aCI6IlJPTEVfQU5PTllNT1VTIiwiZXhwIjoxNjc1Mjc1NzIzfQ.xQSkl14DhQy1YylurrlEwo0jOHNYjl7FpgCUSaZb6ECi7xpvl-L0kDiGoUn4zmdTQCWsW5z2peiTEenmXtTphw" ,
        "Content-Type:application/json;charset=UTF-8" ,
        "Origin:https://portalbnmp.cnj.jus.br",
        "Referer:https://portalbnmp.cnj.jus.br/",
})
@FeignClient(name = "bnmp",url = "https://portalbnmp.cnj.jus.br/bnmpportal/api/pesquisa-pecas/filter")
public interface BNMPClient {


    @PostMapping("?page={page}&size={size}")
    BNMpContentDTO getPessoasMandado(
            @RequestParam Long page,
            @RequestParam Long size,
            @RequestBody BNMpRequestDTO dto , @RequestHeader Map<String, String> headerMap);


}
