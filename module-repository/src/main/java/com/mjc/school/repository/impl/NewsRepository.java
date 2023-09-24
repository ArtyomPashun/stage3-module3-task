package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.repository.utils.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class NewsRepository implements BaseRepository<NewsModel, Long> {

    private final DataSource dataSource;

    @Override
    public List<NewsModel> readAll() {
        return dataSource.getNews();
    }

    @Override
    public Optional<NewsModel> readById(Long id) {
        return dataSource.getNews().stream()
                .filter(news -> news.getId().equals(id)).findFirst();
    }

    @Override
    public NewsModel create(NewsModel entity) {
        List<NewsModel> newsList = dataSource.getNews();
        NewsModel newsModel = NewsModel.builder()
                .id(dataSource.getNextNewsId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .createDate(LocalDateTime.now())
                .lastUpdateDate(LocalDateTime.now())
                .authorId(entity.getAuthorId())
                .build();
        newsList.add(newsModel);

        return newsModel;
    }

    @Override
    public NewsModel update(NewsModel entity) {
        NewsModel newsModel = readById(entity.getId()).get();
        newsModel.setTitle(entity.getTitle());
        newsModel.setContent(entity.getContent());
        newsModel.setAuthorId(entity.getAuthorId());
        newsModel.setLastUpdateDate(LocalDateTime.now());

        return newsModel;
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<NewsModel> optionalNewsModel = readById(id);
        if (optionalNewsModel.isPresent()) {
            List<NewsModel> newsList = dataSource.getNews();
            return newsList.remove(optionalNewsModel.get());
        }
        return false;
    }

    @Override
    public boolean existById(Long id) {
        return dataSource.getNews().stream()
                .anyMatch(news -> news.getId().equals(id));
    }
}
