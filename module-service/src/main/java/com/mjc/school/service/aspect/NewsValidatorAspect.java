package com.mjc.school.service.aspect;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ServiceErrorCode;
import com.mjc.school.service.exceptions.ValidationException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class NewsValidatorAspect {
    BaseRepository<AuthorModel, Long> authorRepository;

    @Autowired
    public NewsValidatorAspect(BaseRepository<AuthorModel, Long> authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Before("@annotation(com.mjc.school.service.annotations.ToValidate) " +
            "&& within(com.mjc.school.service.impl.NewsService) " +
            "&& args(newsDtoRequest)")
    public void doValidate(NewsDtoRequest newsDtoRequest) {
        validateTitle(newsDtoRequest.getTitle());
        validateContent(newsDtoRequest.getContent());
        validateAuthorId(newsDtoRequest.getAuthorId());
    }

    private void validateTitle(String title) {
        if (!(title.length() >= 5 && title.length() <= 30)) {
            throw new ValidationException(String.format(ServiceErrorCode.INCORRECT_NEWS_TITLE.getMessage(), title.length()));
        }
    }

    private void validateContent(String content) {
        if (!(content.length() >= 5 && content.length() <= 255)) {
            throw new ValidationException(String.format(ServiceErrorCode.INCORRECT_NEWS_CONTENT.getMessage(), content.length()));
        }
    }

    private void validateAuthorId(Long id) {
        if (id == null) {
            throw new ValidationException(ServiceErrorCode.NULL_AUTHOR_ID.getMessage());
        }
        if (!authorRepository.existById(id)) {
            throw new NotFoundException(String.format(ServiceErrorCode.AUTHOR_ID_NOT_FOUND.getMessage(), id));
        }
    }
}