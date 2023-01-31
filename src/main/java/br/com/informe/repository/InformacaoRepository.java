package br.com.informe.repository;

import br.com.informe.entity.Informacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InformacaoRepository extends JpaRepository<Informacao, Long> {


    /*@Query("SELECT DISTINCT info FROM Informacao info "
            + " LEFT JOIN info.pessoas p "
            + " LEFT JOIN info.veiculos v "
            + " LEFT JOIN info.marcadores m "
            + " LEFT JOIN m.endereco en  "
            + " ORDER BY info.dataAlteracao DESC ")*/
    @Query("SELECT DISTINCT info FROM Informacao info "
            + " LEFT JOIN info.informePessoas p "
            + " LEFT JOIN info.veiculos v "
            + " LEFT JOIN info.marcadores m "
            + " LEFT JOIN m.endereco en  "
            + " ORDER BY info.dataAlteracao DESC ")

    List<Informacao> retornarTodos();

    /*@Query(value = "SELECT DISTINCT new br.com.informe.dto.InformacaoDTO( info.id, info.titulo , info.detalhe , info.dataAlteracao) " +
            " FROM Informacao info " +
            " WHERE (:id is null or info.id like %:id%) " +
            " AND (:titulo is null or info.titulo like %:titulo%) " +
            " AND (:detalhe is null or info.detalhe like %:detalhe%) " +
            " ORDER BY info.dataAlteracao DESC " +
            "   " )
    List<InformacaoDTO> retornarInformacaoRelatorio();*/


}
