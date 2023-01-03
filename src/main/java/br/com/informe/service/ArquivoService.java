package br.com.informe.service;

import br.com.informe.dto.ArquivoDTO;
import br.com.informe.entity.Arquivo;
import br.com.informe.mapper.Mapper;
import br.com.informe.repository.ArquivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArquivoService {

    @Autowired
    ArquivoRepository repository;

    @Autowired
    Mapper mapper;

    public void deletar(Long id){
        repository.deleteById(id);
    }

    public ArquivoDTO alterar(ArquivoDTO dto){
        Optional<Arquivo> arquivoOptional = repository.findById(dto.getId());
        Arquivo arquivoAlterado = new Arquivo();
        if (arquivoOptional.isPresent() ) {
            arquivoAlterado = arquivoOptional.get();

            if (arquivoAlterado != null) {
                if (dto.getTitulo() != null) {
                    arquivoAlterado.setTitulo(dto.getTitulo());
                }
                if (dto.getDescricao() != null) {
                    arquivoAlterado.setDescricao(dto.getDescricao());
                }
            }
        }
      return  mapper.entityToDTO(repository.save(arquivoAlterado), ArquivoDTO.class);
    }
}
