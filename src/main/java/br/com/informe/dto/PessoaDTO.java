package br.com.informe.dto;

import br.com.informe.entity.InformacaoPessoa;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.util.List;


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
    private String observacao;
    private String cpf;
    private String pai;
    private String mae;
    private String apelido;
    private String linkGenesis;
    private String detalhe;

    /*@JsonManagedReference("informePessoaMan02")
    private List<InformacaoPessoaDTO> informePessoa;*/

    @JsonBackReference(value = "pessoaMang")
    private InformacaoDTO informeArquivo;


}
