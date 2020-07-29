package karol.spring.shopapi.services;

import karol.spring.shopapi.api.v1.mappers.ProducerMapper;
import karol.spring.shopapi.api.v1.models.ProducerDTO;
import karol.spring.shopapi.api.v1.models.ProducerDTOShortView;
import karol.spring.shopapi.exceptions.ValueNotFoundException;
import karol.spring.shopapi.repositories.ProducerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProducerServiceImpl implements ProducerService {

    private final ProducerRepository producerRepository;
    private final ProducerMapper producerMapper;

    public ProducerServiceImpl(ProducerRepository producerRepository, ProducerMapper producerMapper) {
        this.producerRepository = producerRepository;
        this.producerMapper = producerMapper;
    }

    @Override
    public List<ProducerDTOShortView> getAllProducers() {

        return producerRepository.findAll()
                .stream()
                .map(producerMapper::producerToDTOShort)
                .collect(Collectors.toList());
    }

    @Override
    public ProducerDTO getProducerById(Long id) {
        return producerRepository.findById(id)
                .map(producerMapper::producerToProducerDTO)
                .orElseThrow(ValueNotFoundException::new);
    }
}
