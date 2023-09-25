package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.annotations.ToValidate;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ServiceErrorCode;
import com.mjc.school.service.mapper.AuthorMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService implements BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> {

    private final BaseRepository<AuthorModel, Long> authorRepository;
    private final AuthorMapper authorMapper = Mappers.getMapper(AuthorMapper.class);

    @Autowired
    public AuthorService(BaseRepository<AuthorModel, Long> repository) {
        this.authorRepository = repository;
    }

    @Override
    public List<AuthorDtoResponse> readAll() {
        return authorMapper.authorListToAuthorDtoResponseList(authorRepository.readAll());
    }

    @Override
    public AuthorDtoResponse readById(Long id) {
        return authorMapper.authorToDtoResponse(authorRepository.readById(id)
                .orElseThrow(() -> new NotFoundException((String.format(ServiceErrorCode.AUTHOR_ID_NOT_FOUND.getMessage(), id)))));
    }

    @Override
    @ToValidate
    public AuthorDtoResponse create(AuthorDtoRequest createRequest) {
        return authorMapper.authorToDtoResponse(authorRepository.create(authorMapper.authorDtoRequestToAuthor(createRequest)));
    }

    @Override
    @ToValidate
    public AuthorDtoResponse update(AuthorDtoRequest updateRequest) {
        checkAuthorExist(updateRequest.getId());
        return authorMapper.authorToDtoResponse(
                authorRepository.update(
                        authorMapper.authorDtoRequestToAuthor(updateRequest)
                )
        );
    }

    @Override
    public boolean deleteById(Long id) {
        return authorRepository.deleteById(id);
    }

    private void checkAuthorExist(Long id) {
        if (!authorRepository.existById(id))
            throw new NotFoundException(String.format(ServiceErrorCode.AUTHOR_ID_NOT_FOUND.getMessage(), id));
    }
}
