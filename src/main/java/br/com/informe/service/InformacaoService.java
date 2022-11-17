package br.com.informe.service;


import br.com.informe.dto.FiltroInformacaoDTO;
import br.com.informe.dto.InformacaoDTO;
import br.com.informe.entity.Informacao;
import br.com.informe.mapper.Mapper;
import br.com.informe.repository.InformacaoRepository;
import br.com.informe.repository.implementation.InformacaoRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InformacaoService {
    @Autowired
    InformacaoRepository informeRepository;

    @Autowired
    InformacaoRepositoryImpl infoRepImp;

    @Autowired
    Mapper mapper;

    @Transactional
    public List<InformacaoDTO> retornarPorParamentros( FiltroInformacaoDTO filtroInformacaoDTO){
        return  mapper.listEntityToListDTO(infoRepImp.buscarPorParamentros(filtroInformacaoDTO), InformacaoDTO.class);
    }

    @Transactional
    public List<InformacaoDTO> getlAll(){
        List<InformacaoDTO> retorno =  mapper.listEntityToListDTO(informeRepository.findAllByOrderByDataAlteracaoDesc(), InformacaoDTO.class);
        StringBuilder veiculos = new StringBuilder();
        StringBuilder enderecos= new StringBuilder();
        StringBuilder pessoas  = new StringBuilder();
        retorno.forEach( ret -> {

            if (ret.getVeiculos()!= null ) {
                ret.getVeiculos().forEach(veiculoDTO ->
                        veiculos.append(veiculoDTO.getDescricao() + " ; ")
                );
            }
            if ( ret.getPessoas() != null) {
                ret.getPessoas().forEach(pessoaDTO ->
                        pessoas.append(pessoaDTO.getNome() + " ; ")
                );
            }
            if ( ret.getMarcadores() != null && ret.getMarcadores().get(0).getEndereco() != null) {
                ret.getMarcadores().forEach(marcadorDTO ->
                        enderecos.append(marcadorDTO.getEndereco().getDescricao() + " ; ")
                );
            }

            ret.setPessoasMat(pessoas.toString());
            ret.setVeiculosMat(veiculos.toString());
            ret.setEnderecoMat(enderecos.toString());

            pessoas.setLength(0);
            veiculos.setLength(0);
            enderecos.setLength(0);
        });
        return retorno;
    }

    @Transactional
    public InformacaoDTO inserir(InformacaoDTO informacaoDTO){
        Informacao entity = mapper.dTOToEntity(informacaoDTO,Informacao.class);
        entity.setDataAlteracao(LocalDateTime.now());
        entity.setRelevancia(1L);
        entity.setSitucao("infomacao");
        return  mapper.entityToDTO(informeRepository.save(entity),InformacaoDTO.class);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deletar(Long id) {
        Optional<Informacao> optional = informeRepository.findById(id);
        if (optional.isPresent()) {
            Informacao info = optional.get();
            informeRepository.delete(info);
        }
    }

    @Transactional
    public InformacaoDTO getById(Long id) {
        Optional<Informacao> optional = informeRepository.findById(id);
        if (optional.isPresent()) {
            return mapper.entityToDTO(optional.get(),InformacaoDTO.class);
        }else{
            return null;
        }
    }


}
