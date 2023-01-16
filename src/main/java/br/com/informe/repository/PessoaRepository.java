package br.com.informe.repository;

import br.com.informe.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    @Query("SELECT pe FROM Pessoa pe "
            + " WHERE (:nome is null or LOWER(pe.nome) like %:nome%) "
            + " AND (:apelido is null or LOWER(pe.apelido) like %:apelido%) "
            + " AND (:mae is null or LOWER(pe.mae) like %:mae%) "
            + " AND (:pai is null or LOWER(pe.pai) like %:pai%) "
            + " AND (:cpf is null or CAST( regexp_replace(pe.cpf, '[^0-9]', '', 'g') AS string ) like %:cpf%) "
            + " ")
    List<Pessoa> getByParametros(@Param("nome") String nome ,
                                 @Param("cpf") String cpf ,
                                 @Param("apelido") String apelido ,
                                 @Param("mae") String mae ,
                                 @Param("pai") String pai);


    List<Pessoa> findAllByOrderByIdDesc();
}
