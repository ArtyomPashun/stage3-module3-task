package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.AuthorModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthorsRepository implements BaseRepository<AuthorModel, Long> {

    private final EntityManager entityManager;

    @Override
    public List<AuthorModel> readAll() {
        return entityManager.createQuery("select am from AuthorModel am", AuthorModel.class).getResultList();
    }

    @Override
    public Optional<AuthorModel> readById(Long id) {
        return Optional.ofNullable(entityManager.find(AuthorModel.class, id));
    }

    @Override
    public AuthorModel create(AuthorModel entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public AuthorModel update(AuthorModel entity) {
        return entityManager.merge(entity);
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<AuthorModel> optionalAuthorModel = readById(id);
        if (optionalAuthorModel.isPresent()) {
            entityManager.remove(optionalAuthorModel);
            return true;
        }
        return false;
    }

    @Override
    public boolean existById(Long id) {
        AuthorModel authorModel = entityManager.find(AuthorModel.class, id);
        return authorModel != null;
    }
}
