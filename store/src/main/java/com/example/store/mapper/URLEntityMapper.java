package com.example.store.mapper;

import com.example.domain.model.URL;
import com.example.store.entity.URLEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface URLEntityMapper {

    URLEntity toEntity(URL url);
    URL toDomain(URLEntity urlEntity);
}
