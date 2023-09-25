package com.mjc.school.controller;

import com.mjc.school.controller.annotations.CommandHandler;
import com.mjc.school.controller.impl.AuthorController;
import com.mjc.school.controller.impl.NewsController;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.exceptions.ServiceErrorCode;
import com.mjc.school.service.exceptions.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class ControllerHandler {
    private final AuthorController authorController;
    private final NewsController newsController;

    @CommandHandler(command = 1)
    public void getAllNews() {
        System.out.println(Operations.GET_ALL_NEWS.getOperation());
        newsController.readAll().forEach(System.out::println);
    }

    @CommandHandler(command = 2)
    public void getNewsById() {
        System.out.println(Operations.GET_NEWS_BY_ID.getOperation());
        Long id = readNumber("News Id");
        System.out.println(newsController.readById(id));
    }

    @CommandHandler(command = 3)
    public void createNews() {
        System.out.println(Operations.CREATE_NEWS.getOperation());
        NewsDtoRequest dto = readNewsValues(null);
        System.out.println(newsController.create(dto));
    }

    @CommandHandler(command = 4)
    public void updateNews() {
        System.out.println(Operations.UPDATE_NEWS.getOperation());
        Long id = readNumber("News id");
        NewsDtoRequest dto = readNewsValues(id);
        System.out.println(newsController.update(dto));
    }

    @CommandHandler(command = 5)
    public void deleteNewsById() {
        Long id = readNumber("News id");
        System.out.println(newsController.deleteById(id));
    }

    @CommandHandler(command = 6)
    public void getAllAuthors() {
        System.out.println(Operations.GET_ALL_AUTHORS.getOperation());
        authorController.readAll().forEach(System.out::println);
    }

    @CommandHandler(command = 7)
    public void getAuthorById() {
        System.out.println(Operations.GET_AUTHOR_BY_ID.getOperation());
        Long id = readNumber("Author Id");
        System.out.println(authorController.readById(id));
    }

    @CommandHandler(command = 8)
    public void createAuthor() {
        System.out.println(Operations.CREATE_AUTHOR.getOperation());
        AuthorDtoRequest dto = readAuthorValues(null);
        System.out.println(authorController.create(dto));
    }

    @CommandHandler(command = 9)
    public void updateAuthor() {
        System.out.println(Operations.UPDATE_AUTHOR.getOperation());
        Long id = readNumber("Author id");
        AuthorDtoRequest dto = readAuthorValues(id);
        System.out.println(authorController.update(dto));
    }

    @CommandHandler(command = 10)
    public void deleteAuthorById() {
        System.out.println(Operations.DELETE_AUTHOR_BY_ID.getOperation());
        Long id = readNumber("Author id");
        System.out.println(authorController.deleteById(id));
    }

    private Long readNumber(String type) {
        System.out.println("Enter " + type + ":");
        try (Scanner sc = new Scanner(System.in)) {
            return sc.nextLong();
        } catch (Exception e) {
            throw new ValidationException(String.format(ServiceErrorCode.CHECK_SHOULD_BE_NUMBER.getMessage(), type));
        }
    }

    private AuthorDtoRequest readAuthorValues(Long id) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter author name: ");
        String name = sc.nextLine();
        sc.close();
        return new AuthorDtoRequest(id, name);
    }

    private NewsDtoRequest readNewsValues(Long id) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter news title:");
        String title = sc.nextLine();
        System.out.println("Enter news content:");
        String content = sc.nextLine();
        System.out.println("Enter author id:");
        Long authorId = readNumber("Author Id");
        sc.close();
        return new NewsDtoRequest(id, title, content, authorId);
    }

}
