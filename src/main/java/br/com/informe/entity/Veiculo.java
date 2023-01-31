package br.com.informe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


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

    @Column(name = "informacoes")
    private String informacoes;
    @Column(name = "proprietario")
    private String proprietario;
    @Column(name = "endereco")
    private String endereco;

    @Column(name = "carater_geral" , columnDefinition="VARCHAR DEFAULT N")
    private String caraterGeral;

    @Column(name = "dt_ultima_alteracao")
    private LocalDateTime dataAlteracao;

    @Column(name = "status_carater_geral")
    private String statusCaraterGeral;

    @Column(name = "desfecho_carater_geral")
    private String desfechoCaraterGeral;


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
