package br.com.informe.repository;

import br.com.informe.entity.Marcador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcadorRepository  extends JpaRepository<Marcador, Long> {

}
