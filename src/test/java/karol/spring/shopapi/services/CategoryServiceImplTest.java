package karol.spring.shopapi.services;

import karol.spring.shopapi.api.v1.mappers.CategoryMapper;
import karol.spring.shopapi.api.v1.models.CategoryDTO;
import karol.spring.shopapi.models.Category;
import karol.spring.shopapi.repositories.CategoryRepository;
import karol.spring.shopapi.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CategoryServiceImplTest {

    @Mock
    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    ProductRepository productRepository;

    CategoryService service;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        service = new CategoryServiceImpl(categoryRepository, categoryMapper, productRepository);
    }

    @Test
    void getAllCategories() {

        when(categoryRepository.findAll()).thenReturn(Arrays.asList(new Category(), new Category()));

        List<CategoryDTO> categoriesDTO = service.getAllCategories();

        assertEquals(2, categoriesDTO.size());
    }

    @Test
    void createNewCategory() {

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        categoryDTO.setName("test");

        Category savedCategory = new Category();
        savedCategory.setId(categoryDTO.getId());
        savedCategory.setName(categoryDTO.getName());

        when(categoryRepository.save(any(Category.class))).thenReturn(savedCategory);

        //todo

    }

    @Test
    void updateCategoryById() {
    }
}