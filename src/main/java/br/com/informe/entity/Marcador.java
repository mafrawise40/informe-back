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
@Table(schema = "informe", name = "tb_marcador")
public class Marcador extends EntityBase<Long>{

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_marcador")
    private Long id;

    @Column(name = "latitude", columnDefinition = "float8")
    private double latitude;
    @Column(name = "longitude" , columnDefinition = "float8")
    private double longitude;

    //exibir a informação window aberto ou não
    @Column(name = "is_open")
    private boolean open;

    //dizer qual tipo de informação, o icone também pode ser alterado por aqui
    @Column(name = "tipo")
    private String tipo;

    //salvar a url do icone do google maps
    @Column(name = "url_icone")
    private String tipoIcone;

    @ManyToOne(fetch=FetchType.LAZY )
    @JoinColumn(name="id_informacao")
    @JsonBackReference
    private Informacao informeMarcador;

    @OneToOne(cascade = CascadeType.ALL , fetch=FetchType.LAZY)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id_endereco")
    Endereco endereco;

    @Override
    public Long getId() {
        return id;
    }



    @JsonIgnore
    public Informacao getInformeMarcador() {
        return informeMarcador;
    }
    @JsonIgnore
    public void setInformeMarcador(Informacao informeMarcador) {
        this.informeMarcador = informeMarcador;
    }
}
