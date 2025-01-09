package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.TagModel;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
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
public class TagServiceTest {

    @Mock
    private BaseRepository<TagModel, Long> tagRepository;

    @InjectMocks
    private TagService tagService;

    private TagModel tagModel;
    private TagDtoRequest tagDtoRequest;
    private TagDtoResponse tagDtoResponse;

    @BeforeEach
    void setUp() {
        tagModel = new TagModel();
        tagModel.setId(1L);
        tagModel.setName("Technology");

        tagDtoRequest = new TagDtoRequest();
        tagDtoRequest.setId(1L);
        tagDtoRequest.setName("Technology");

        tagDtoResponse = new TagDtoResponse();
        tagDtoResponse.setId(1L);
        tagDtoResponse.setName("Technology");
    }

    @Test
    void testReadAll() {
        when(tagRepository.readAll()).thenReturn(List.of(tagModel));

        List<TagDtoResponse> responses = tagService.readAll();

        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals("Technology", responses.get(0).getName());
    }

    @Test
    void testReadById() {
        when(tagRepository.readById(1L)).thenReturn(Optional.of(tagModel));

        TagDtoResponse response = tagService.readById(1L);

        assertNotNull(response);
        assertEquals("Technology", response.getName());
    }

    @Test
    void testReadByIdNotFound() {
        when(tagRepository.readById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> tagService.readById(1L));
    }

    @Test
    void testCreate() {
        when(tagRepository.create(any(TagModel.class))).thenReturn(tagModel);

        TagDtoResponse response = tagService.create(tagDtoRequest);

        assertNotNull(response);
        assertEquals("Technology", response.getName());
    }

    @Test
    void testUpdate() {
        when(tagRepository.existById(1L)).thenReturn(true);
        when(tagRepository.update(any(TagModel.class))).thenReturn(tagModel);

        TagDtoResponse response = tagService.update(tagDtoRequest);

        assertNotNull(response);
        assertEquals("Technology", response.getName());
    }

    @Test
    void testUpdateNotFound() {
        when(tagRepository.existById(1L)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> tagService.update(tagDtoRequest));
    }

    @Test
    void testDeleteById() {
        when(tagRepository.deleteById(1L)).thenReturn(true);

        assertTrue(tagService.deleteById(1L));
    }
}
