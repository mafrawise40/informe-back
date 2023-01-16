package br.com.informe.service;

import br.com.informe.dto.ArquivoPessoaDTO;
import br.com.informe.entity.ArquivoPessoa;
import br.com.informe.mapper.Mapper;
import br.com.informe.repository.ArquivoPessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArquivoPessoaService {

    @Autowired
    ArquivoPessoaRepository repository;

    @Autowired
    Mapper mapper;

    public void deletar(Long id){
        repository.deleteById(id);
    }

    public ArquivoPessoaDTO alterar(ArquivoPessoaDTO dto){
        Optional<ArquivoPessoa> arquivoOptional = repository.findById(dto.getId());
        ArquivoPessoa arquivoAlterado = new ArquivoPessoa();
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
        return  mapper.entityToDTO(repository.save(arquivoAlterado), ArquivoPessoaDTO.class);
    }
}
