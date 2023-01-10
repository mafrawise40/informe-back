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
            + " WHERE (:nome is null or pe.nome like %:nome%) "
            + " ")
    List<Pessoa> getByParametros(@Param("nome") String nome);
}
