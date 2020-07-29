package karol.spring.shopapi.controllers;

import karol.spring.shopapi.api.v1.models.ProductDTO;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.ArgumentMatchers.any;

import karol.spring.shopapi.api.v1.models.ProductDTOShortView;
import karol.spring.shopapi.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProductControllerTest {

    private final String URL = "/api/v1/product/";

    @Mock
    ProductService productService;

    @InjectMocks
    ProductController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getProductById() throws Exception {

        ProductDTO product = new ProductDTO();
        product.setId(1L);
        product.setName("test");

        when(productService.getProductById(anyLong())).thenReturn(product);

        mockMvc.perform(get(URL + "1")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("test")))
                .andExpect(jsonPath("$.id", equalTo(1)));
    }

    @Test
    void getAllProductsShortView() throws Exception {
        ProductDTOShortView product1 = new ProductDTOShortView();
        product1.setName("test_1");

        ProductDTOShortView product2 = new ProductDTOShortView();
        product2.setName("test_2");

        when(productService.getAllProductShortView()).thenReturn(Arrays.asList(product1, product2));

        mockMvc.perform(get(URL)
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productsShortView", hasSize(2)));
    }

    @Test
    void createNewProduct() throws Exception {

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("test");

        ProductDTO returnDTO = new ProductDTO();
        returnDTO.setId(productDTO.getId());
        returnDTO.setName(productDTO.getName());

        when(productService.createNewProduct(any(ProductDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(post(URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(AbstractRestControllerTest.asJsonString(productDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("test")))
                .andExpect(jsonPath("$.id", equalTo(1)));
    }

    @Test
    void deleteProductById() throws Exception {

        mockMvc.perform(delete(URL + "1")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(productService, times(1)).deleteById(anyLong());
    }

    @Test
    void updateProduct() throws Exception {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("test");

        ProductDTO returnDTO = new ProductDTO();
        returnDTO.setId(productDTO.getId());
        returnDTO.setName("new test");

        when(productService.updateProductById(anyLong(), any(ProductDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(patch(URL + "1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(AbstractRestControllerTest.asJsonString(productDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("new test")));
    }
}