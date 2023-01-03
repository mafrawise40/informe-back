package br.com.informe.service;

import br.com.informe.entity.Marcador;
import br.com.informe.repository.MarcadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MarcadorService {

    @Autowired
    MarcadorRepository marcadorRepository;
    @Transactional(propagation = Propagation.REQUIRED)
    public void deletar(Long id) {
        Optional<Marcador> optional = marcadorRepository.findById(id);
        if (optional.isPresent()) {
            Marcador info = optional.get();
            marcadorRepository.delete(info);
        }
    }
}
