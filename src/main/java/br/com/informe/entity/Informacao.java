package br.com.informe.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.CascadeType.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Setter
@Getter
@Table(schema = "informe", name = "tb_informacao")
public class Informacao extends EntityBase<Long>{

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_informacao")
    private Long id;


    @Column(name = "titulo")
    private String titulo;

    @Column(name = "detalhe" , columnDefinition = "text")
    private String detalhe;

    @JsonManagedReference
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @OneToMany(mappedBy="informe", cascade = {PERSIST, MERGE, REFRESH, DETACH} , fetch=FetchType.LAZY )
    private List<Pessoa> pessoas;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JsonManagedReference
    @OneToMany(mappedBy="informeVeiculo",  cascade = {PERSIST, MERGE, REFRESH, DETACH} , fetch=FetchType.LAZY )
    private List<Veiculo> veiculos;


    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JsonManagedReference
    @OneToMany(mappedBy="informeMarcador", cascade = CascadeType.ALL , fetch=FetchType.LAZY )
    private List<Marcador> marcadores;


    @Column(name = "situacao")    @JsonManagedReference
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @OneToMany(mappedBy="informeArquivo", cascade = {PERSIST, MERGE, REFRESH, DETACH} , fetch=FetchType.LAZY )
    private List<Arquivo> arquivos;

    private String situcao;

    @Column(name = "relevancia")
    private Long relevancia;

    @Column(name = "dt_ultima_alteracao")
    private LocalDateTime dataAlteracao;

    @Column(name = "dt_inclusao")
    private LocalDateTime dataInclusao;

    @Override
    public Long getId() {
        return id;
    }

}
