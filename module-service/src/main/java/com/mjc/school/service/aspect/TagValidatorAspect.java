package com.mjc.school.service.aspect;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.TagModel;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ServiceErrorCode;
import com.mjc.school.service.exceptions.ValidationException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TagValidatorAspect {
    BaseRepository<TagModel, Long> tagRepository;

    @Autowired
    public TagValidatorAspect(BaseRepository<TagModel, Long> tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Before("@annotation(com.mjc.school.service.annotations.ToValidate) " +
            "&& within(com.mjc.school.service.impl.TagService) " +
            "&& args(tagDtoRequest)")
    public void doValidate(TagDtoRequest tagDtoRequest) {
        validateName(tagDtoRequest.getName());
        validateTagId(tagDtoRequest.getId());
    }

    private void validateName(String title) {
        if (!(title.length() >= 3 && title.length() <= 15)) {
            throw new ValidationException(String.format(ServiceErrorCode.INCORRECT_TAG_NAME.getMessage(), title.length()));
        }
    }

    private void validateTagId(Long id) {
        if (id == null) {
            throw new ValidationException(ServiceErrorCode.NULL_TAG_ID.getMessage());
        }
        if (!tagRepository.existById(id)) {
            throw new NotFoundException(String.format(ServiceErrorCode.TAG_ID_NOT_FOUND.getMessage(), id));
        }
    }
}