package karol.spring.shopapi.services;

import karol.spring.shopapi.api.v1.models.ProducerDTO;
import karol.spring.shopapi.api.v1.models.ProducerDTOShortView;

import java.util.List;

public interface ProducerService {

    List<ProducerDTOShortView> getAllProducers();

    ProducerDTO getProducerById(Long id);

    ProducerDTO createNewProducer(ProducerDTO producerDTO);
}
