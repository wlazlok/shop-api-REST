package karol.spring.shopapi.controllers;

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

}
