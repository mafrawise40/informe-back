package br.com.informe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Setter
@Getter
@Table(schema = "informe", name = "tb_pessoa")
public class Pessoa extends EntityBase<Long>{

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pessoa")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "rg")
    private String rg;

    @Column(name = "situacao")
    private String situacao;

    @Column(name = "observacao")
    private String  observacao;

    @Column(name = "cpf")
    private String cpf;


    @JsonBackReference
    @ManyToOne(fetch=FetchType.LAZY )
    @JoinColumn(name="id_informacao", nullable=false)
    private Informacao informe;

    @JsonIgnore
    public Informacao getInforme() {
        return informe;
    }

    @JsonIgnore
    public void setInforme(Informacao informe) {
        this.informe = informe;
    }

    @Override
    public Long getId() {
        return id;
    }
}
