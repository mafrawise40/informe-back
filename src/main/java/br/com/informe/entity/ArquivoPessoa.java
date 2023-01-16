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
@Table(schema = "informe", name = "tb_arquivo_pessoa")
public class ArquivoPessoa extends   EntityBase<Long> {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_arquivo")
    private Long id;

    @Column(name="descricao")
    private String descricao;

    @Column(name="titulo")
    private String titulo;

    @Lob
    private byte[] arquivo;


    @JsonBackReference
    @ManyToOne(fetch=FetchType.LAZY )
    @JoinColumn(name="id_pessoa", nullable=true)
    private Pessoa pessoaArquivo;

    @JsonIgnore
    public Pessoa getInformeArquivo() {
        return pessoaArquivo;
    }

    @JsonIgnore
    public void setPessoaArquivo(Pessoa pessoaArquivo) {
        this.pessoaArquivo = pessoaArquivo;
    }



    @Override
    public Long getId() {
        return id;
    }
}
