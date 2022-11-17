package br.com.informe.entity;


import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(schema = "informe", name = "tb_endereco")
public class Endereco   extends EntityBase<Long>{

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Long id;


    @Column(name = "descricao")
    private String descricao;

    @Column(name = "regiao")
    private String regiao;

    @Column(name = "uf")
    private String uf;


    @Override
    public Long getId() {
        return id;
    }
}
