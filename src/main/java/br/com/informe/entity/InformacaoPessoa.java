package br.com.informe.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    //@JsonBackReference("informePessoaEntity")
    @JsonBackReference("informePessoaEntity")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_informacao")
    private Informacao informacao;

    //@JsonBackReference("informePessoaEntity2")
    @JsonManagedReference("informePessoaEntity2")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_pessoa")
    private Pessoa pessoa;
}
