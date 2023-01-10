package br.com.informe.dto;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class InformacaoPessoaDTO extends  BaseDTO {

    private Long id;


    @JsonBackReference("informePessoaMan01")
    private InformacaoDTO informacao;
    //@JsonBackReference("informePessoaMan02")
    @JsonBackReference("informePessoaMan02")
    private PessoaDTO pessoa;
}
