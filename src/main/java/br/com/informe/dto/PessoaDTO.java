package br.com.informe.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private PessoaDTO pessoaDTO;
    private String nascimento;

    private String mandado;
    private String foragido;

    @JsonBackReference(value = "pessoaMang")
    private InformacaoDTO informeArquivo;

    @JsonManagedReference(value = "pessoaArquivo")
    private List<ArquivoPessoaDTO> arquivos;



    //TODO Lista de mandados para cada pessoa.
    //TODO melhor o robô para extrair foto das pessoas no genesis
    //TODO CORRIGIR A FORMA QUE A TABELA DE RELATORIO GERA..

}
