package com.example.boundary.mapper;

import com.example.boundary.dto.URLDTO;
import com.example.domain.model.URLModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface URLDTOMapper {

    URLDTO toDTO(URLModel urlModel);
    URLModel toDomain(URLDTO urldto);
}
