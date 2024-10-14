package com.example.store.mapper;

import com.example.domain.model.URLModel;
import com.example.store.entity.URLEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface URLEntityMapper {

    URLEntity toEntity(URLModel urlModel);
    URLModel toDomain(URLEntity urlEntity);
}
