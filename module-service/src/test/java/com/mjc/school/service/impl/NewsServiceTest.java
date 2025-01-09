package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
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
public class NewsServiceTest {

    @Mock
    private BaseRepository<NewsModel, Long> newsRepository;

    @InjectMocks
    private NewsService newsService;

    private NewsModel newsModel;
    private NewsDtoRequest newsDtoRequest;
    private NewsDtoResponse newsDtoResponse;

    @BeforeEach
    void setUp() {
        newsModel = new NewsModel();
        newsModel.setId(1L);
        newsModel.setTitle("Breaking News");
        newsModel.setContent("Some important news content");

        newsDtoRequest = new NewsDtoRequest();
        newsDtoRequest.setId(1L);
        newsDtoRequest.setTitle("Breaking News");
        newsDtoRequest.setContent("Some important news content");

        newsDtoResponse = new NewsDtoResponse();
        newsDtoResponse.setId(1L);
        newsDtoResponse.setTitle("Breaking News");
        newsDtoResponse.setContent("Some important news content");
    }

    @Test
    void testReadAll() {
        when(newsRepository.readAll()).thenReturn(List.of(newsModel));

        List<NewsDtoResponse> responses = newsService.readAll();

        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals("Breaking News", responses.get(0).getTitle());
    }

    @Test
    void testReadById() {
        when(newsRepository.readById(1L)).thenReturn(Optional.of(newsModel));

        NewsDtoResponse response = newsService.readById(1L);

        assertNotNull(response);
        assertEquals("Breaking News", response.getTitle());
    }

    @Test
    void testReadByIdNotFound() {
        when(newsRepository.readById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> newsService.readById(1L));
    }

    @Test
    void testCreate() {
        when(newsRepository.create(any(NewsModel.class))).thenReturn(newsModel);

        NewsDtoResponse response = newsService.create(newsDtoRequest);

        assertNotNull(response);
        assertEquals("Breaking News", response.getTitle());
    }

    @Test
    void testUpdate() {
        when(newsRepository.existById(1L)).thenReturn(true);
        when(newsRepository.update(any(NewsModel.class))).thenReturn(newsModel);

        NewsDtoResponse response = newsService.update(newsDtoRequest);

        assertNotNull(response);
        assertEquals("Breaking News", response.getTitle());
    }

    @Test
    void testUpdateNotFound() {
        when(newsRepository.existById(1L)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> newsService.update(newsDtoRequest));
    }

    @Test
    void testDeleteById() {
        when(newsRepository.deleteById(1L)).thenReturn(true);

        assertTrue(newsService.deleteById(1L));
    }
}
