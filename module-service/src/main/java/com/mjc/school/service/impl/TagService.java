package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.TagModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.annotations.ToValidate;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ServiceErrorCode;
import com.mjc.school.service.mapper.TagMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService implements BaseService<TagDtoRequest, TagDtoResponse, Long> {

    private final BaseRepository<TagModel, Long> tagRepository;
    private final TagMapper tagMapper = Mappers.getMapper(TagMapper.class);

    @Autowired
    public TagService(BaseRepository<TagModel, Long> repository) {
        this.tagRepository = repository;
    }

    @Override
    public List<TagDtoResponse> readAll() {
        return tagMapper.listTagToListTagDtoResponse(tagRepository.readAll());
    }

    @Override
    public TagDtoResponse readById(Long id) {
        return tagMapper.tagToTagDtoResponse(tagRepository.readById(id)
                .orElseThrow(() -> new NotFoundException((String.format(ServiceErrorCode.TAG_ID_NOT_FOUND.getMessage(), id)))));
    }

    @Override
    @ToValidate
    public TagDtoResponse create(TagDtoRequest createRequest) {
        return tagMapper.tagToTagDtoResponse(tagRepository.create(tagMapper.tagDtoRequestToTag(createRequest)));
    }

    @Override
    @ToValidate
    public TagDtoResponse update(TagDtoRequest updateRequest) {
        checkTagExist(updateRequest.getId());
        return tagMapper.tagToTagDtoResponse(tagRepository.update(tagMapper.tagDtoRequestToTag(updateRequest)));
    }

    @Override
    public boolean deleteById(Long id) {
        return tagRepository.deleteById(id);
    }

    private void checkTagExist(Long id) {
        if (!tagRepository.existById(id)) {
            throw new NotFoundException(String.format(ServiceErrorCode.TAG_ID_NOT_FOUND.getMessage(), id));
        }
    }
}
