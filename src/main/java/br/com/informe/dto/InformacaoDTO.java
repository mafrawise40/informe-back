package br.com.informe.dto;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class InformacaoDTO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String titulo;
    private String detalhe;
    @JsonManagedReference(value = "pessoaMang")
    private List<PessoaDTO> pessoas;
    @JsonManagedReference(value = "veiculoMang")
    private List<VeiculoDTO> veiculos;
    @JsonManagedReference(value = "marcaoresMang")
    private List<MarcadorDTO> marcadores;
    @JsonManagedReference(value = "arquivoMang")
    private List<ArquivoDTO> arquivos;
    private String situcao;
    private Long relevancia;
    private LocalDateTime dataAlteracao;

    //@JsonManagedReference("informePessoaMan01")
    private List<InformacaoPessoaDTO> informePessoas;

    //exibição mat-table para ser melhor filtrado
    private String pessoasMat;
    private String veiculosMat;
    private String enderecoMat;

    //mat-chip saber quais pessoas foram removidas para deletar do banco de dados
    private List<Long> pessoasRemovidas;
    private List<Long> veiculosRemovido;


    public InformacaoDTO(Long id , String titulo , String detalhe, LocalDateTime dataAlteracao){
        this.id = id;
        this.titulo = titulo;
        this.detalhe = detalhe;
        this.dataAlteracao = dataAlteracao;
    }

}
