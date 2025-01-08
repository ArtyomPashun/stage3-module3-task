package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.impl.TagModel;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TagMapper {

    TagModel tagDtoRequestToTag(TagDtoRequest tagDtoRequest);

    List<TagDtoResponse> listTagToListTagDtoResponse(List<TagModel> tags);

    TagDtoResponse tagToTagDtoResponse(TagModel tagModel);
}
