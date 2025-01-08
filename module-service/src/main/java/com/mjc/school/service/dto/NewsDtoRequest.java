package com.mjc.school.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsDtoRequest {
    private long id;
    private String title;
    private String content;
    private long authorId;
    private List<Long> tags;
}
