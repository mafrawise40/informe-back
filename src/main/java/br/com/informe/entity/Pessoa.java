package br.com.informe.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.*;


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

    @Column(name = "observacao",  columnDefinition = "TEXT")
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

    @Column(name = "nascimento")
    private String nascimento;

    @Column(name = "mandado")
    private String mandado;

    @Column(name = "foragido" , columnDefinition = "VARCHAR(1)")
    private String foragido;

    @JsonIgnore
    @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY, cascade = {PERSIST, MERGE, REFRESH, DETACH})
    private List<InformacaoPessoa> informePessoa;


    @JsonManagedReference
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @OneToMany(mappedBy="pessoaArquivo",  fetch=FetchType.LAZY )
    private List<ArquivoPessoa> arquivos;

    @Override
    public Long getId() {
        return id;
    }
}
