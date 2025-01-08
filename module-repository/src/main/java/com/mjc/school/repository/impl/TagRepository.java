package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.TagModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TagRepository implements BaseRepository<TagModel, Long> {

    private final EntityManager entityManager;

    @Override
    public List<TagModel> readAll() {
        return entityManager.createQuery("select tm from TagModel tm", TagModel.class).getResultList();
    }

    @Override
    public Optional<TagModel> readById(Long id) {
        return Optional.ofNullable(entityManager.find(TagModel.class, id));
    }

    @Override
    public TagModel create(TagModel entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public TagModel update(TagModel entity) {
        return entityManager.merge(entity);
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<TagModel> optionalTagModel = readById(id);
        if (optionalTagModel.isPresent()) {
            entityManager.remove(optionalTagModel);
            return true;
        }
        return false;
    }

    @Override
    public boolean existById(Long id) {
        TagModel tagModel = entityManager.find(TagModel.class, id);
        return tagModel != null;
    }
}
