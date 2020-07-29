package karol.spring.shopapi.controllers;

import karol.spring.shopapi.api.v1.models.CategoryDTO;
import karol.spring.shopapi.api.v1.models.CategoryDTOShortView;
import karol.spring.shopapi.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CategoryControllerTest {

    private final String URL = "/api/v1/categories/";

    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getAllCategories() throws Exception {

        CategoryDTOShortView category1 = new CategoryDTOShortView();
        category1.setName("test_1");

        CategoryDTOShortView category2 = new CategoryDTOShortView();
        category2.setName("test_2");

        when(categoryService.getAllCategoriesShortView()).thenReturn(Arrays.asList(category1, category2));

        mockMvc.perform(get(URL)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoriesShort", hasSize(2)));
    }

    @Test
    void createNewCategory() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("test");

        CategoryDTO returnDTO = new CategoryDTO();
        returnDTO.setName(categoryDTO.getName());

        when(categoryService.createNewCategory(any(CategoryDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(post(URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(AbstractRestControllerTest.asJsonString(categoryDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("test")));
    }

    @Test
    void deleteCategoryById() throws Exception {

        mockMvc.perform(delete(URL + "1")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(categoryService, times(1)).deleteById(anyLong());
    }

    @Test
    void getCategoryById() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        categoryDTO.setName("test");

        when(categoryService.getCategoryById(anyLong())).thenReturn(categoryDTO);

        mockMvc.perform(get(URL + "1")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("test")))
                .andExpect(jsonPath("$.id", equalTo(1)));
    }

    @Test
    void updateCategory() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        categoryDTO.setName("test");

        CategoryDTO returnDTO = new CategoryDTO();
        returnDTO.setId(categoryDTO.getId());
        returnDTO.setName("new test");

        when(categoryService.updateCategoryById(anyLong(), any(CategoryDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(patch(URL + 1)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(AbstractRestControllerTest.asJsonString(categoryDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("new test")))
                .andExpect(jsonPath("$.id", equalTo(1)));
    }
}