package br.com.informe.service;

import br.com.informe.dto.PessoaDTO;
import br.com.informe.entity.Pessoa;
import br.com.informe.mapper.Mapper;
import br.com.informe.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository repository;

    @Autowired
    Mapper mapper;

    @Transactional
    public List<PessoaDTO> getlAll(){
        return mapper.listEntityToListDTO(repository.findAll(), PessoaDTO.class);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deletar(Long id) {
        Optional<Pessoa> optional = repository.findById(id);
        if (optional.isPresent()) {
            Pessoa pessoaDeletada = optional.get();
            pessoaDeletada.setInforme(null);

            repository.deleteById(pessoaDeletada.getId());
        }
    }

    @Transactional
    public PessoaDTO inserir(PessoaDTO pessoaDTO){
        Pessoa entity = mapper.dTOToEntity(pessoaDTO,Pessoa.class);
        return  mapper.entityToDTO(repository.save(entity),PessoaDTO.class);
    }

    @Transactional
    public PessoaDTO getById(Long id) {
        Optional<Pessoa> optional = repository.findById(id);
        if (optional.isPresent()) {
            return mapper.entityToDTO(optional.get(),PessoaDTO.class);
        }else{
            return null;
        }
    }

}
