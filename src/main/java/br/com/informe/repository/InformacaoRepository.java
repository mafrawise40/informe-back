package br.com.informe.repository;

import br.com.informe.entity.Informacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InformacaoRepository extends JpaRepository<Informacao, Long> {

    List<Informacao> findAllByOrderByDataAlteracaoDesc();
}
