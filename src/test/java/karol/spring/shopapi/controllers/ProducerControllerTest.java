package karol.spring.shopapi.controllers;

import karol.spring.shopapi.api.v1.models.ProducerDTO;
import karol.spring.shopapi.api.v1.models.ProducerDTOShortView;
import static org.mockito.ArgumentMatchers.any;
import karol.spring.shopapi.services.ProducerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProducerControllerTest {

    private final String URL = "/api/v1/producer/";

    @Mock
    ProducerService producerService;

    @InjectMocks
    ProducerController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getAllProducers() throws Exception {
        ProducerDTOShortView producer1 = new ProducerDTOShortView();
        producer1.setName("test_1");

        ProducerDTOShortView producer2 = new ProducerDTOShortView();
        producer2.setName("test_2");

        when(producerService.getAllProducers()).thenReturn(Arrays.asList(producer1, producer2));

        mockMvc.perform(get(URL)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.producers", hasSize(2)));

    }

    @Test
    void getProducerById() throws Exception {
        ProducerDTO producerDTO = new ProducerDTO();
        producerDTO.setId(1L);
        producerDTO.setName("test");

        when(producerService.getProducerById(anyLong())).thenReturn(producerDTO);

        mockMvc.perform(get(URL + "1")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("test")))
                .andExpect(jsonPath("$.id", equalTo(1)));
    }

    @Test
    void createNewProducer() throws Exception {
        ProducerDTO producerDTO = new ProducerDTO();
        producerDTO.setId(1L);
        producerDTO.setName("test");

        ProducerDTO returnDTO = new ProducerDTO();
        returnDTO.setId(producerDTO.getId());
        returnDTO.setName(producerDTO.getName());

        when(producerService.createNewProducer(any(ProducerDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(post(URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(AbstractRestControllerTest.asJsonString(producerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("test")))
                .andExpect(jsonPath("$.id", equalTo(1)));
    }

    @Test
    void deleteProducerById() throws Exception {
        mockMvc.perform(delete(URL + "1")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(producerService, times(1)).deleteProducerById(anyLong());
    }

    @Test
    void updateProducer() throws Exception {

        ProducerDTO producerDTO = new ProducerDTO();
        producerDTO.setId(1L);
        producerDTO.setName("test");

        ProducerDTO returnDTO = new ProducerDTO();
        returnDTO.setId(producerDTO.getId());
        returnDTO.setName("new test");

        when(producerService.updateProducer(anyLong(), any(ProducerDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(patch(URL + "1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(AbstractRestControllerTest.asJsonString(producerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("new test")))
                .equals(jsonPath("$.id", equalTo(1)));

    }
}