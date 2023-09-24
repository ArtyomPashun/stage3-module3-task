package com.mjc.school.repository.utils;

import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.repository.data.AuthorData;
import com.mjc.school.repository.data.NewsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSource {

    private final NewsData newsData;
    private final AuthorData authorData;

    public List<NewsModel> getNews() {
        return newsData.getNewsList();
    }

    public List<AuthorModel> getAuthors() {
        return authorData.getAuthorsList();
    }

    public long getNextNewsId() {
        return NewsData.getNextId();
    }

    public long getNextAuthorId() {
        return AuthorData.getNextId();
    }
}