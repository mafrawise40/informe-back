package br.com.informe.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PessoaDTO  extends BaseDTO  {

    private static final long serialVersionUID = 1L;


    private Long id;
    private String nome;
    private String rg;
    private String situacao;
    private String  observacao;
    private String cpf;

    @JsonBackReference(value = "pessoaMang")
    private InformacaoDTO informeArquivo;


}
