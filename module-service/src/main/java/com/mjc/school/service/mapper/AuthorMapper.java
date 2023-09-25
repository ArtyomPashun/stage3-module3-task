package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface AuthorMapper {
    List<AuthorDtoResponse> authorListToAuthorDtoResponseList(List<AuthorModel> authorModelList);

    AuthorDtoResponse authorToDtoResponse(AuthorModel authorModel);


    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "lastUpdateDate", ignore = true)
    AuthorModel authorDtoRequestToAuthor(AuthorDtoRequest authorDtoRequest);
}
