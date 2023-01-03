package br.com.informe.dto;


import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EnderecoDTO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String descricao;
    private String observacao;
    private String regiao;
    private String uf;
}
