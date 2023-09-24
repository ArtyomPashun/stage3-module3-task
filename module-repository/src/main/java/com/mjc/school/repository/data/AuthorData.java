package com.mjc.school.repository.data;

import com.mjc.school.repository.model.impl.AuthorModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.mjc.school.repository.utils.DataReader.getLinesFromFile;

@Component
public class AuthorData {

    private static final String AUTHORS_FILE_NAME = "authors";
    private static long id = 1;
    private List<AuthorModel> authorsList;

    public AuthorData() {
        init();
    }

    public List<AuthorModel> getAuthorsList() {
        return authorsList;
    }

    public static long getNextId() {
        return id++;
    }

    private void init() {
        authorsList = new ArrayList<>();
        List<String> authorsList = getLinesFromFile(AUTHORS_FILE_NAME);
        for (String name : authorsList) {
            this.authorsList.add(
                    new AuthorModel(
                            getNextId(),
                            name,
                            LocalDateTime.now(),
                            LocalDateTime.now()
                    ));
        }
    }
}