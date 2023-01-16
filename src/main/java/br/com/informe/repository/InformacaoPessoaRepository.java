package br.com.informe.repository;

import br.com.informe.entity.InformacaoPessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InformacaoPessoaRepository extends JpaRepository<InformacaoPessoa, Long> {

    @Query(
            "SELECT informePessoa FROM InformacaoPessoa informePessoa" +
                    " WHERE (:infoId is null or informePessoa.informacao.id = :infoId) " +
                    " AND   (:pessoaId is null or informePessoa.pessoa.id = :pessoaId)  "
    )
    Optional<InformacaoPessoa> findByInformacaoAndPessoa(Long infoId, Long pessoaId);


    @Query(
            "SELECT informePessoa FROM InformacaoPessoa informePessoa" +
                    " WHERE (:infoId is null or informePessoa.informacao.id = :infoId) " +
                    " AND   (:pessoaId is null or informePessoa.pessoa.id = :pessoaId)  "
    )
    List<InformacaoPessoa> getByInformacaoAndPessoa(Long infoId, Long pessoaId);


    @Query(
            "SELECT informePessoa FROM InformacaoPessoa informePessoa " +
                    " WHERE informePessoa.informacao.id = (SELECT informePessoaInt.informacao.id FROM InformacaoPessoa informePessoaInt " +
                    "                                        WHERE informePessoaInt.pessoa.id = :pessoaId " +
                    "                                       ) "
    )
    List<InformacaoPessoa> getVinculosPessoas(Long pessoaId);
}


