package com.proiectps.JobBoard;

import com.proiectps.JobBoard.model.Category;
import com.proiectps.JobBoard.repository.CategoryRepository;
import com.proiectps.JobBoard.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CategoryServiceTest {

    private static final String NAME = "Software Development";
    private static final String NAME_NOT = "Non-existing Category";

    private CategoryServiceImpl categoryServiceImpl;

    @Mock
    private CategoryRepository categoryRepository;

    private Category category;

    @BeforeEach
    void init() {
        initMocks(this);
        category = new Category();
        category.setName(NAME);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(categoryRepository.findByName(NAME)).thenReturn(category);
    }

    @Test
    void givenExistingName_whenFindByName_thenFindOne() {
        categoryServiceImpl = new CategoryServiceImpl(categoryRepository);

        Category foundCategory = categoryServiceImpl.findByName(NAME);

        assertNotNull(foundCategory);
        assertEquals(NAME, foundCategory.getName());
    }

    @Test
    void givenNonExistingName_whenFindByName_thenNull() {
        when(categoryRepository.findByName(NAME_NOT)).thenReturn(null);

        categoryServiceImpl = new CategoryServiceImpl(categoryRepository);
        Category foundCategory = categoryServiceImpl.findByName(NAME_NOT);

        assertNull(foundCategory);
    }
}
