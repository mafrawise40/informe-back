package br.com.informe.service;

import br.com.informe.dto.VeiculoDTO;
import br.com.informe.entity.Veiculo;
import br.com.informe.mapper.Mapper;
import br.com.informe.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    VeiculoRepository veiculoRepository;

    @Autowired
    Mapper mapper;

    @Transactional
    public List<VeiculoDTO> getAll(){
        return mapper.listEntityToListDTO(veiculoRepository.findAllByOrderByIdDesc(), VeiculoDTO.class);
    }

    @Transactional
    public List<VeiculoDTO> getAllCaraterGeral(){
        return mapper.listEntityToListDTO(veiculoRepository.getAllVeiculosCaraterGeral(), VeiculoDTO.class);
    }

    @Transactional
    public VeiculoDTO inserir(VeiculoDTO veiculoDTO){

        Veiculo entity = mapper.dTOToEntity(veiculoDTO,Veiculo.class);
        if ( veiculoDTO.getCaraterGeral().equals("true")){
            entity.setCaraterGeral("S");
        }else{
            entity.setCaraterGeral("N");
        }
        return  mapper.entityToDTO(veiculoRepository.save(entity),VeiculoDTO.class);
    }

    @Transactional
    public VeiculoDTO atualizar(VeiculoDTO veiculoDTO){
        Optional<Veiculo> optionalVeiculo = veiculoRepository.findById(veiculoDTO.getId());
        if ( optionalVeiculo.isPresent()) {

            optionalVeiculo.get().setDataAlteracao(LocalDateTime.now());

            if ( veiculoDTO.getPlaca() != null) {
                optionalVeiculo.get().setPlaca(veiculoDTO.getPlaca());
            }

            if ( veiculoDTO.getDescricao() != null) {
                optionalVeiculo.get().setDescricao(veiculoDTO.getDescricao());
            }

            if ( veiculoDTO.getProprietario() != null) {
                optionalVeiculo.get().setProprietario(veiculoDTO.getProprietario());
            }
            if ( veiculoDTO.getEndereco() != null) {
                optionalVeiculo.get().setEndereco(veiculoDTO.getEndereco());
            }
            if ( veiculoDTO.getInformacoes() != null) {
                optionalVeiculo.get().setInformacoes(veiculoDTO.getInformacoes());
            }
            if ( veiculoDTO.getCaraterGeral() != null) {
                optionalVeiculo.get().setCaraterGeral(veiculoDTO.getCaraterGeral());
            }


            if ( veiculoDTO.getCaraterGeral().equals("true")){
                optionalVeiculo.get().setCaraterGeral("S");
            }else{
                optionalVeiculo.get().setCaraterGeral("N");
            }

            return  mapper.entityToDTO(veiculoRepository.save(optionalVeiculo.get()),VeiculoDTO.class);
        }
        return null;

    }

    @Transactional
    public VeiculoDTO incluirCaraterGeral(Long id){
        Optional<Veiculo> veiculo = veiculoRepository.findById(id);
        if ( veiculo.isPresent()) {
            veiculo.get().setCaraterGeral("S");
            return mapper.entityToDTO(veiculoRepository.save(veiculo.get()) , VeiculoDTO.class);
        }
        return null;
    }


    @Transactional
    public VeiculoDTO incluirDesfechoCaraterGeral(VeiculoDTO dto){
        Optional<Veiculo> veiculo = veiculoRepository.findById(dto.getId());
        if ( veiculo.isPresent()) {
            veiculo.get().setCaraterGeral("N");
            veiculo.get().setDesfechoCaraterGeral(dto.getDesfechoCaraterGeral());
            veiculo.get().setDataAlteracao(LocalDateTime.now());
            return mapper.entityToDTO(veiculoRepository.save(veiculo.get()) , VeiculoDTO.class);
        }
        return null;
    }



    @Transactional
    public VeiculoDTO getById(Long id) {
        Optional<Veiculo> optional = veiculoRepository.findById(id);
        if (optional.isPresent()) {
            return mapper.entityToDTO(optional.get(),VeiculoDTO.class);
        }else{
            return null;
        }
    }
}
