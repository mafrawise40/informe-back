package br.com.informe.dto.cliente;

import br.com.informe.dto.BaseDTO;
import lombok.*;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BNMpDTO  extends BaseDTO {

    //campo de busca
    /*private boolean buscaOrgaoRecursivo;
    private long idEstado;
    private long idMunicipio;*/

    //campo listagem

    private long id;
    private String numeroPeca;
    private String numeroProcesso;
    private String nomePessoa;
    private String alcunha;
    private String descricaoStatus;
    private LocalDate dataExpedicao;
    private String nomeOrgao;
    private String descricaoPeca;
    private long idTipoPeca;
    private String nomeMae;
    private String nomePai;
    private String descricaoSexo;
    private String descricaoProfissao;
    private LocalDate dataNascimento;
    private String numeroPecaAnterior;
    private String dataNascimentoFormatada;
    private String dataExpedicaoFormatada;
    private String numeroPecaFormatado;

}
