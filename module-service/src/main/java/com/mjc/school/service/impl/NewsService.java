package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.impl.NewsRepository;
import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.annotations.ToValidate;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ServiceErrorCode;
import com.mjc.school.service.mapper.NewsMapper;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService implements BaseService<NewsDtoRequest, NewsDtoResponse, Long> {

    private final BaseRepository<NewsModel, Long> newsRepository;
    private final NewsMapper newsMapper = Mappers.getMapper(NewsMapper.class);

    @Autowired
    public NewsService(BaseRepository<NewsModel, Long> repository) {
        this.newsRepository = repository;
    }

    @Override
    public List<NewsDtoResponse> readAll() {
        return newsMapper.newsListToNewsDtoResponseList(newsRepository.readAll());
    }

    @Override
    public NewsDtoResponse readById(Long id) {
        return newsMapper.newsToDtoResponse(newsRepository.readById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ServiceErrorCode.NEWS_ID_DOES_NOT_EXIST.getMessage(), id))));
    }

    @ToValidate
    @Override
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
        return newsMapper.newsToDtoResponse(newsRepository.create(newsMapper.newsDtoRequestToNews(createRequest)));
    }

    @ToValidate
    @Override
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        checkNewsExist(updateRequest.getId());
        return newsMapper.newsToDtoResponse(
                newsRepository.update(
                        newsMapper.newsDtoRequestToNews(updateRequest)
                )
        );

    }

    @Override
    public boolean deleteById(Long id) {
        return newsRepository.deleteById(id);
    }

    private void checkNewsExist(Long id) {
        if (!newsRepository.existById(id)) {
            throw new NotFoundException(String.format(ServiceErrorCode.NEWS_ID_DOES_NOT_EXIST.getMessage(), id));
        }
    }
}
