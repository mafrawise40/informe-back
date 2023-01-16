package br.com.informe.entity;


import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Setter
@Getter
@Table(schema = "informe", name = "ta_informacao_pessoa")
public class InformacaoPessoa  extends EntityBase<Long>{

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_informacao_pessoa")
    private Long id;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_informacao")
    @JsonIgnore
    private Informacao informacao;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_pessoa")
    private Pessoa pessoa;

    @Column(name = "envolvimento")
    private String envolvimento;
}
