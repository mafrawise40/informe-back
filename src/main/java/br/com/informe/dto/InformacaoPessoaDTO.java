package br.com.informe.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class InformacaoPessoaDTO extends  BaseDTO {

    private Long id;


    @JsonIgnore
    private InformacaoDTO informacao;
    private PessoaDTO pessoa;
    private String envolvimento;


}
