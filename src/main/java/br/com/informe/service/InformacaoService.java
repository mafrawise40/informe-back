package br.com.informe.service;


import br.com.informe.dto.ArquivoDTO;
import br.com.informe.dto.FiltroInformacaoDTO;
import br.com.informe.dto.InformacaoDTO;
import br.com.informe.entity.*;
import br.com.informe.mapper.Mapper;
import br.com.informe.repository.ArquivoRepository;
import br.com.informe.repository.InformacaoRepository;
import br.com.informe.repository.PessoaRepository;
import br.com.informe.repository.VeiculoRepository;
import br.com.informe.repository.implementation.InformacaoRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InformacaoService {
    @Autowired
    InformacaoRepository informeRepository;

    @Autowired
    InformacaoRepositoryImpl infoRepImp;

    @Autowired
    VeiculoRepository veiculoRepository;

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    ArquivoRepository arquivoRepository;

    @Autowired
    Mapper mapper;

    @Transactional
    public List<InformacaoDTO> retornarPorParamentros( FiltroInformacaoDTO filtroInformacaoDTO){
        List<Informacao> listEnty = infoRepImp.buscarPorParamentros(filtroInformacaoDTO);
        listEnty.forEach(informacao -> {
            informacao.setArquivos(null);
           // informacao.setPessoas(null);
            informacao.setVeiculos(null);
        });
        return  mapper.listEntityToListDTO(listEnty, InformacaoDTO.class);
    }

    @Transactional
    public List<InformacaoDTO> getlAll(){


        List<Informacao> listEnty = informeRepository.retornarTodos();
        listEnty.forEach(informacao ->  informacao.setArquivos(null));
        List<InformacaoDTO> retorno =  mapper.listEntityToListDTO( listEnty, InformacaoDTO.class);

        StringBuilder veiculos = new StringBuilder();
        StringBuilder enderecos= new StringBuilder();
        StringBuilder pessoas  = new StringBuilder();
        retorno.forEach( ret -> {
            ret.setArquivos(null);
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
            /*if ( ret.getInformePessoas() != null) {
                ret.getInformePessoas().forEach(pessoaDTO ->
                        pessoas.append(pessoaDTO.getPessoa().getNome() + " ; ")
                );
            }*/
            if ( ret.getMarcadores() != null && ret.getMarcadores().get(0).getEndereco() != null) {
                ret.getMarcadores().forEach(marcadorDTO -> {
                    if (marcadorDTO.getEndereco() != null && marcadorDTO.getEndereco().getDescricao() != null) {
                        enderecos.append(marcadorDTO.getEndereco().getDescricao() + " ; ");
                    }
                });
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
        entity.setDataInclusao(LocalDateTime.now());
        entity.setRelevancia(1L);
        entity.setSitucao("infomacao");

        //por enquanto
      /* List<InformacaoPessoa> newInformePessoaList = new ArrayList<>();
        informacaoDTO.getPessoas().forEach(pessoa -> {
            InformacaoPessoa newInformePessoa = InformacaoPessoa.builder()
                    .informacao(entity)
                    .pessoa(mapper.dTOToEntity(pessoa , Pessoa.class))
                    .build();
            newInformePessoaList.add(newInformePessoa);
        });

        entity.setInformePessoas(newInformePessoaList);*/

        return  mapper.entityToDTO(informeRepository.save(entity),InformacaoDTO.class);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deletar(Long id) {
        Optional<Informacao> optional = informeRepository.findById(id);
        if (optional.isPresent()) {
            Informacao info = optional.get();
            info.getVeiculos().stream().forEach(veiculo -> {
                veiculo.setInformeVeiculo(null);
                veiculoRepository.save(veiculo);
            });
          /*  info.getPessoas().forEach(pessoa -> {
                pessoa.setInforme(null);
                pessoaRepository.save(pessoa);
            });*/
            info.getArquivos().forEach(arquivo -> {
                arquivo.setInformeArquivo(null);
                arquivoRepository.save(arquivo);
            });
            info = informeRepository.save(info);
            informeRepository.delete(info);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deletar(Long id, Long idImagem) {
        Optional<Informacao> optional = informeRepository.findById(id);
        if (optional.isPresent()) {
            Informacao info = optional.get();

            List<Arquivo> listFiltro = info.getArquivos().stream().filter( arquivo -> {
                return arquivo.getId().equals(idImagem);
            }).collect(Collectors.toList());

            listFiltro.forEach(arquivo ->{
                arquivo.setInformeArquivo(null);
                arquivoRepository.deleteById(arquivo.getId()); });
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

    @Transactional
    public InformacaoDTO update(InformacaoDTO dto)  {
        Informacao informacao = mapper.dTOToEntity(dto, Informacao.class);
        informacao.setDataAlteracao(LocalDateTime.now());

        //recupera a lista de veiculos e pessoas removidas
        if ( dto.getVeiculosRemovido() != null && !dto.getVeiculosRemovido().isEmpty()){
            dto.getVeiculosRemovido().forEach( id -> {
                if ( id != 0) {
                    Veiculo veiculo = veiculoRepository.findById(id).get();
                    veiculo.setInformeVeiculo(null);
                    veiculoRepository.save(veiculo);
                }
            });
        }

        if ( dto.getPessoasRemovidas() != null && !dto.getPessoasRemovidas().isEmpty()){
            dto.getPessoasRemovidas().forEach( id -> {
                Pessoa pessoa = pessoaRepository.findById(id).get();
                //pessoa.setInforme(null);
                pessoaRepository.save(pessoa);
            });
        }

        return mapper.entityToDTO(informeRepository.save(informacao) , InformacaoDTO.class);
    }

    @Transactional
    public List<ArquivoDTO> uploadFotos(Map<String, MultipartFile> allRequestParams ,
                                        Map<String, String> allComprimidos,
                                        Map<String,String> tituloImagem,
                                        Long idInformacao) {

        Informacao informe = Informacao.builder().id(idInformacao).build();
        List<ArquivoDTO> retorno = new ArrayList<>();
        allRequestParams.forEach((key, multipartFile) ->{
            try {



                Arquivo arquivo = Arquivo.builder().arquivo(multipartFile.getBytes())
                        .descricao(multipartFile.getOriginalFilename())
                        .informeArquivo(informe).build();

                if( allComprimidos.containsKey(key) ) {
                    //arquivo.setArquivoComprimido(allComprimidos.get(key));
                }
                if( tituloImagem.containsKey(key) ) {
                    arquivo.setTitulo(tituloImagem.get(key));
                }

                retorno.add(mapper.entityToDTO( arquivoRepository.save(arquivo) , ArquivoDTO.class ));


            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        return retorno;
    }


}
