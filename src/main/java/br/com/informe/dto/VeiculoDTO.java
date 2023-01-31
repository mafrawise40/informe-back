package br.com.informe.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import java.time.LocalDateTime;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class VeiculoDTO extends BaseDTO  {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String descricao;
    private String placa;

    private String informacoes;

    private String proprietario;
    private String endereco;
    private String caraterGeral;
    private String statusCaraterGeral;
    private String desfechoCaraterGeral;
    private LocalDateTime dataAlteracao;

    @JsonBackReference(value = "veiculoMang")
    private InformacaoDTO informeArquivo;

}
