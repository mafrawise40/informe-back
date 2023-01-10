package br.com.informe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.DETACH;


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

    @Column(name = "pai")
    private String pai;

    @Column(name = "mae")
    private String mae;

    @Column(name = "apelido")
    private String apelido;
    @Column(name = "linkGenesis")
    private String linkGenesis;

    @Column(name = "detalhe", columnDefinition = "TEXT")
    private String detalhe;


    @JsonBackReference
    @ManyToOne(fetch=FetchType.LAZY )
    @JoinColumn(name="id_informacao", nullable=true)
    private Informacao informe;


   // @JsonManagedReference("informePessoaEntity2")
   /* @JsonBackReference("informePessoaEntity2")
    @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY, cascade = {PERSIST, MERGE, REFRESH, DETACH}, orphanRemoval = true)
    private List<InformacaoPessoa> informePessoa;*/

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
