package karol.spring.shopapi.controllers;

import karol.spring.shopapi.api.v1.models.ProducerDTO;
import karol.spring.shopapi.api.v1.models.ProducerDTOShortViewList;
import karol.spring.shopapi.services.ProducerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/producer/")
public class ProducerController {

    private final ProducerService producerService;

    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private ProducerDTOShortViewList getAllProducers(){
        return new ProducerDTOShortViewList(producerService.getAllProducers());
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    private ProducerDTO getProducerById(@PathVariable Long id){
        return producerService.getProducerById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private ProducerDTO createNewProducer(@RequestBody ProducerDTO producerDTO){
        return producerService.createNewProducer(producerDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    private void deleteProducerById(@PathVariable Long id){
        producerService.deleteProducerById(id);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    private ProducerDTO updateProducer(@PathVariable Long id, @RequestBody ProducerDTO producerDTO){
        return producerService.updateProducer(id, producerDTO);
    }
}