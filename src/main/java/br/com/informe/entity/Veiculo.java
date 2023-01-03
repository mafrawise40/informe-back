package br.com.informe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;



@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@Entity
@Table(schema = "informe", name = "tb_veiculo")
public class Veiculo extends EntityBase<Long>{

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_veiculo")
    private Long id;


    @Column(name = "descricao")
    private String descricao;

    @Column(name = "placa")
    private String placa;


    @ManyToOne(fetch=FetchType.LAZY )
    @JoinColumn(name="id_informacao")
    @JsonBackReference
    private Informacao informeVeiculo;

    @Override
    public Long getId() {
        return id;
    }

    @JsonIgnore
    public Informacao getInformeVeiculo() {
        return informeVeiculo;
    }

    @JsonIgnore
    public void setInformeVeiculo(Informacao informeVeiculo) {
        this.informeVeiculo = informeVeiculo;
    }
}
