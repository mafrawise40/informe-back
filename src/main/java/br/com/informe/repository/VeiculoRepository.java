package br.com.informe.repository;

import br.com.informe.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    @Query(" SELECT v FROM Veiculo v " +
            " WHERE v.caraterGeral = 'S'  " +
            " ORDER BY v.id DESC " +
            " ")
    List<Veiculo> getAllVeiculosCaraterGeral();

    List<Veiculo> findAllByOrderByIdDesc();
}
