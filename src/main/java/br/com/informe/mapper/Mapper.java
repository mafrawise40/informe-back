package br.com.informe.mapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Mapper {

    @Autowired
    ObjectMapper objectMapper;


    @SneakyThrows
    /**Converte uma Lista de Entity em uma Lista de Dto.
     *
     * Primeiro converte uma lista de Entity em JSON String
     * Em seguida transforma a JSON String em EntityDTO.
     */
    public <S,T> List<S> listEntityToListDTO(List<T> entityList , Class<S> dtoClass){
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_ABSENT);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        CollectionType javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, dtoClass);
        return objectMapper.readValue(objectMapper.writeValueAsString(entityList) , javaType);
    }


    @SneakyThrows
    public <S,T> S dTOToEntity(T dto , Class<S> entityClass){
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_ABSENT);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        return objectMapper.readValue(objectMapper.writeValueAsString(dto),entityClass);
    }

    @SneakyThrows
    public <S,T> S entityToDTO(T entity , Class<S> dtoClass){
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_ABSENT);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        return objectMapper.readValue(objectMapper.writeValueAsString(entity),dtoClass);
    }




}
