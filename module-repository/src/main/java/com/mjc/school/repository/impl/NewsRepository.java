package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.NewsModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class NewsRepository implements BaseRepository<NewsModel, Long> {

    private final EntityManager entityManager;

    @Override
    public List<NewsModel> readAll() {
        return entityManager.createQuery("select nm from NewsModel nm", NewsModel.class).getResultList();
    }

    @Override
    public Optional<NewsModel> readById(Long id) {
        return Optional.ofNullable(entityManager.find(NewsModel.class, id));
    }

    @Override
    public NewsModel create(NewsModel entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public NewsModel update(NewsModel entity) {
        return entityManager.merge(entity);
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<NewsModel> optionalNewsModel = readById(id);
        if (optionalNewsModel.isPresent()) {
            entityManager.remove(optionalNewsModel);
            return true;
        }
        return false;
    }

    @Override
    public boolean existById(Long id) {
        NewsModel newsModel = entityManager.find(NewsModel.class, id);
        return newsModel != null;
    }
}
