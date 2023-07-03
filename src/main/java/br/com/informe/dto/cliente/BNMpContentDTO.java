package br.com.informe.dto.cliente;

import br.com.informe.dto.BaseDTO;
import lombok.*;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BNMpContentDTO  extends BaseDTO {

    private List<BNMpDTO> content;

    boolean last;
    long totalElements;
    long totalPages;
    String sort;
    boolean first;
    long numberOfElements;
    long size;
    long number;
}
