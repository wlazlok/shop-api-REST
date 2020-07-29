package karol.spring.shopapi.services;

import karol.spring.shopapi.api.v1.models.ProducerDTO;
import karol.spring.shopapi.api.v1.models.ProducerDTOShortView;
import karol.spring.shopapi.models.Producer;

import java.util.List;

public interface ProducerService {

    List<ProducerDTOShortView> getAllProducers();

    ProducerDTO getProducerById(Long id);

    ProducerDTO createNewProducer(ProducerDTO producerDTO);

    void deleteProducerById(Long id);

    ProducerDTO updateProducer(Long id, ProducerDTO producerDTO);
}
