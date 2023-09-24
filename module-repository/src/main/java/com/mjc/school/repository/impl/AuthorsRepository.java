package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.repository.utils.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthorsRepository implements BaseRepository<AuthorModel, Long> {

    private final DataSource dataSource;

    @Override
    public List<AuthorModel> readAll() {
        return dataSource.getAuthors();
    }

    @Override
    public Optional<AuthorModel> readById(Long id) {
        return dataSource.getAuthors().stream()
                .filter(author -> author.getId().equals(id))
                .findFirst();
    }

    @Override
    public AuthorModel create(AuthorModel entity) {
        List<AuthorModel> authorList = dataSource.getAuthors();
        AuthorModel author = AuthorModel.builder()
                .id(dataSource.getNextAuthorId())
                .name(entity.getName())
                .createDate(LocalDateTime.now())
                .lastUpdateDate(LocalDateTime.now())
                .build();
        authorList.add(author);

        return author;
    }

    @Override
    public AuthorModel update(AuthorModel entity) {
        AuthorModel authorModel = readById(entity.getId()).get();
        authorModel.setName(entity.getName());
        authorModel.setLastUpdateDate(LocalDateTime.now());

        return authorModel;
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<AuthorModel> optionalAuthorModel = readById(id);
        if (optionalAuthorModel.isPresent()) {
            List<AuthorModel> authorsList = dataSource.getAuthors();
            return authorsList.remove(optionalAuthorModel.get());
        }
        return false;
    }

    @Override
    public boolean existById(Long id) {
        return dataSource.getAuthors().stream()
                .anyMatch(author -> author.getId().equals(id));
    }
}
