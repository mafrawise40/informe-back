package br.com.informe.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ArquivoPessoaDTO extends BaseDTO {

    private static final long serialVersionUID = 1L;


    private Long id;
    private String descricao;
    private byte[] arquivo;
    private String titulo;

    @JsonBackReference(value = "pessoaArquivo")
    private PessoaDTO pessoaArquivo;
}
