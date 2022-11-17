package br.com.informe.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MarcadorDTO  extends BaseDTO {

    private static final long serialVersionUID = 1L;

    private Long id;
    private double latitude;
    private double longitude;
    //exibir a informação window aberto ou não
    private String open;
    //dizer qual tipo de informação, o icone também pode ser alterado por aqui
    private String tipo;
    //salvar a url do icone do google maps
    private String tipoIcone;
    private EnderecoDTO endereco;

    @JsonBackReference(value = "marcaoresMang")
    private InformacaoDTO informeArquivo;

}
