package br.com.informe.dto.cliente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BNMpRequestDTO {

    private boolean buscaOrgaoRecursivo;
    private long idEstado;
    private long idMunicipio;
}
