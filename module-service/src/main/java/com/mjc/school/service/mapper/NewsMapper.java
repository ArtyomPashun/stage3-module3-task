package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface NewsMapper {
    List<NewsDtoResponse> newsListToNewsDtoResponseList(List<NewsModel> newsModelList);

    NewsDtoResponse newsToDtoResponse(NewsModel news);

    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "lastUpdateDate", ignore = true)
    NewsModel newsDtoRequestToNews(NewsDtoRequest newsRequest);
}
