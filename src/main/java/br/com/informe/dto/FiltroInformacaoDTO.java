package br.com.informe.dto;


import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FiltroInformacaoDTO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    String campoPesquisa;
    LocalDateTime dataInicial;
    LocalDateTime dataFinal;


}
