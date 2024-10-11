package com.example.boundary.mapper;

import com.example.boundary.dto.URLDTO;
import com.example.domain.model.URL;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface URLDTOMapper {

    URLDTO toDTO(URL url);
    URL toDomain(URLDTO urldto);
}
