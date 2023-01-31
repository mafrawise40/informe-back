package br.com.informe.service;

import br.com.informe.dto.InformacaoPessoaDTO;
import br.com.informe.dto.PessoaDTO;
import br.com.informe.entity.ArquivoPessoa;
import br.com.informe.entity.InformacaoPessoa;
import br.com.informe.entity.Pessoa;
import br.com.informe.mapper.Mapper;
import br.com.informe.repository.ArquivoPessoaRepository;
import br.com.informe.repository.InformacaoPessoaRepository;
import br.com.informe.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository repository;

    @Autowired
    ArquivoPessoaRepository repositoryArquivo;

    @Autowired
    InformacaoPessoaRepository informacaoPessoaRepository;

    @Autowired
    Mapper mapper;
    @Autowired
    private ArquivoPessoaRepository arquivoRepository;

    @Transactional
    public List<PessoaDTO> getlAll(){
        return mapper.listEntityToListDTO(repository.findAllByOrderByIdDesc(), PessoaDTO.class);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deletar(Long id) {
        Optional<Pessoa> optional = repository.findById(id);
        if (optional.isPresent()) {
            Pessoa pessoaDeletada = optional.get();

            if ( informacaoPessoaRepository.getByInformacaoAndPessoa(null,pessoaDeletada.getId()).isEmpty()) {

                pessoaDeletada.getArquivos().forEach( arquivoPessoa -> {
                    arquivoRepository.deleteById(arquivoPessoa.getId());
                });

                repository.deleteById(pessoaDeletada.getId());
            }else {
                throw new RuntimeException("Pessoa possui vínculos no sistema");
            }

        }
    }

    @Transactional
    public PessoaDTO inserir(PessoaDTO pessoaDTO){
        Pessoa entity = mapper.dTOToEntity(pessoaDTO,Pessoa.class);
        return  mapper.entityToDTO(repository.save(entity),PessoaDTO.class);
    }

    @Transactional
    public PessoaDTO atualizar(PessoaDTO pessoaDTO){
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

    @Transactional
    public List<PessoaDTO> getByParametros(PessoaDTO dto) {

        if ( dto.getNome() != null){
            dto.setNome(dto.getNome().toLowerCase());
        }

        if ( dto.getPai() != null){
            dto.setPai(dto.getPai().toLowerCase());
        }

        if ( dto.getMae() != null){
            dto.setMae(dto.getMae().toLowerCase());
        }

        if ( dto.getApelido() != null){
            dto.setApelido(dto.getApelido().toLowerCase());
        }

        if ( dto.getCpf() != null) { //remover os pontos e traços
            dto.setCpf(dto.getCpf().replaceAll("[\\D]", ""));
        }

        List<Pessoa> retorno = repository.getByParametros(dto.getNome() , dto.getCpf() , dto.getApelido() , dto.getMae() , dto.getPai());

        return mapper.listEntityToListDTO(retorno,PessoaDTO.class);

    }

    @Transactional
    public List<InformacaoPessoaDTO> getVinculosPessoas(Long id) {
        List<InformacaoPessoa> vinculos = new ArrayList<>();
                informacaoPessoaRepository.getVinculosPessoas(id);
        if (!vinculos.isEmpty()) {
            return mapper.listEntityToListDTO(vinculos,InformacaoPessoaDTO.class);
        }else{
            return null;
        }
    }


    @Transactional
    public void uploadFotos(Map<String, MultipartFile> allRequestParams ,
                            Map<String, String> allComprimidos,
                            Map<String,String> tituloImagem,
                            Long idPessoa) {

        Pessoa pessoa = repository.getOne(idPessoa);

        List<ArquivoPessoa> arquivoPessoaList = new ArrayList<>();

        allRequestParams.forEach((key, multipartFile) ->{

            ArquivoPessoa arquivoPessoa = null;
            try {
                arquivoPessoa = ArquivoPessoa.builder().arquivo(multipartFile.getBytes())
                        .descricao(multipartFile.getOriginalFilename())
                        .build();

                arquivoPessoa.setPessoaArquivo(pessoa);

                if( allComprimidos.containsKey(key) ) {
                    //arquivo.setArquivoComprimido(allComprimidos.get(key));
                }
                if( tituloImagem.containsKey(key) ) {
                    arquivoPessoa.setTitulo(tituloImagem.get(key));
                }

                arquivoPessoaList.add(arquivoPessoa);
                repositoryArquivo.save(arquivoPessoa);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }



        });
        pessoa.setArquivos(arquivoPessoaList);

    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void deletar(Long id, Long idImagem) {
        Optional<Pessoa> optional = repository.findById(id);
        if (optional.isPresent()) {
            Pessoa pessoa = optional.get();

            List<ArquivoPessoa> listFiltro = pessoa.getArquivos().stream().filter(arquivo -> {
                return arquivo.getId().equals(idImagem);
            }).collect(Collectors.toList());

            listFiltro.forEach(arquivo ->{
                arquivo.setPessoaArquivo(null);
                arquivoRepository.deleteById(arquivo.getId()); });
        }
    }

}
