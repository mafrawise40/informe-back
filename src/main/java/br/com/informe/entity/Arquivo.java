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
@Table(schema = "informe", name = "tb_arquivo")
public class Arquivo extends EntityBase<Long> {

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
    @JoinColumn(name="id_informacao", nullable=true)
    private Informacao informeArquivo;

    @JsonIgnore
    public Informacao getInformeArquivo() {
        return informeArquivo;
    }

    @JsonIgnore
    public void setInformeArquivo(Informacao informeArquivo) {
        this.informeArquivo = informeArquivo;
    }



    @Override
    public Long getId() {
        return id;
    }
}
