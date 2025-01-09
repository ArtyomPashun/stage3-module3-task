package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @Mock
    private BaseRepository<AuthorModel, Long> authorRepository;

    @InjectMocks
    private AuthorService authorService;

    private AuthorModel authorModel;
    private AuthorDtoRequest authorDtoRequest;
    private AuthorDtoResponse authorDtoResponse;

    @BeforeEach
    void setUp() {
        authorModel = new AuthorModel();
        authorModel.setId(1L);
        authorModel.setName("John Doe");

        authorDtoRequest = new AuthorDtoRequest();
        authorDtoRequest.setId(1L);
        authorDtoRequest.setName("John Doe");

        authorDtoResponse = new AuthorDtoResponse();
        authorDtoResponse.setId(1L);
        authorDtoResponse.setName("John Doe");
    }

    @Test
    void testReadAll() {
        when(authorRepository.readAll()).thenReturn(List.of(authorModel));

        List<AuthorDtoResponse> responses = authorService.readAll();

        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals("John Doe", responses.get(0).getName());
    }

    @Test
    void testReadById() {
        when(authorRepository.readById(1L)).thenReturn(Optional.of(authorModel));

        AuthorDtoResponse response = authorService.readById(1L);

        assertNotNull(response);
        assertEquals("John Doe", response.getName());
    }

    @Test
    void testReadByIdNotFound() {
        when(authorRepository.readById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> authorService.readById(1L));
    }

    @Test
    void testCreate() {
        when(authorRepository.create(any(AuthorModel.class))).thenReturn(authorModel);

        AuthorDtoResponse response = authorService.create(authorDtoRequest);

        assertNotNull(response);
        assertEquals("John Doe", response.getName());
    }

    @Test
    void testUpdate() {
        when(authorRepository.existById(1L)).thenReturn(true);
        when(authorRepository.update(any(AuthorModel.class))).thenReturn(authorModel);

        AuthorDtoResponse response = authorService.update(authorDtoRequest);

        assertNotNull(response);
        assertEquals("John Doe", response.getName());
    }

    @Test
    void testUpdateNotFound() {
        when(authorRepository.existById(1L)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> authorService.update(authorDtoRequest));
    }

    @Test
    void testDeleteById() {
        when(authorRepository.deleteById(1L)).thenReturn(true);

        assertTrue(authorService.deleteById(1L));
    }
}
