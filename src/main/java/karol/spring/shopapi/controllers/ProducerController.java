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
    public ProducerDTOShortViewList getAllProducers(){
        return new ProducerDTOShortViewList(producerService.getAllProducers());
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProducerDTO getProducerById(@PathVariable Long id){
        return producerService.getProducerById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProducerDTO createNewProducer(@RequestBody ProducerDTO producerDTO){
        return producerService.createNewProducer(producerDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProducerById(@PathVariable Long id){
        producerService.deleteProducerById(id);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProducerDTO updateProducer(@PathVariable Long id, @RequestBody ProducerDTO producerDTO){
        return producerService.updateProducer(id, producerDTO);
    }
}
