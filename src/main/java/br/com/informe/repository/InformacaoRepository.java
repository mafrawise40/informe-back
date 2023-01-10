package br.com.informe.repository;

import br.com.informe.entity.Informacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InformacaoRepository extends JpaRepository<Informacao, Long> {
   /*@Query("SELECT DISTINCT info FROM Informacao info "
            + " LEFT JOIN info.informePessoas p "
            + " LEFT JOIN info.veiculos v "
            + " LEFT JOIN info.marcadores m "
            + " LEFT JOIN m.endereco en  "
            + " ORDER BY info.dataAlteracao DESC ") */


    @Query("SELECT DISTINCT info FROM Informacao info "
            + " LEFT JOIN info.pessoas p "
            + " LEFT JOIN info.veiculos v "
            + " LEFT JOIN info.marcadores m "
            + " LEFT JOIN m.endereco en  "
            + " ORDER BY info.dataAlteracao DESC ")
    List<Informacao> retornarTodos();


}
