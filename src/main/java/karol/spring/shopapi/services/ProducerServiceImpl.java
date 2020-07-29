package karol.spring.shopapi.services;

import karol.spring.shopapi.api.v1.mappers.ProducerMapper;
import karol.spring.shopapi.api.v1.models.CategoryDTO;
import karol.spring.shopapi.api.v1.models.ProducerDTO;
import karol.spring.shopapi.api.v1.models.ProducerDTOShortView;
import karol.spring.shopapi.exceptions.NullValueException;
import karol.spring.shopapi.exceptions.ValueExsistException;
import karol.spring.shopapi.exceptions.ValueNotFoundException;
import karol.spring.shopapi.models.Category;
import karol.spring.shopapi.models.Producer;
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

    @Override
    public ProducerDTO createNewProducer(ProducerDTO producerDTO) {

        Producer producer = producerMapper.producerDTOToProducer(producerDTO);

        checkIfValueNotExsistInBase(producerDTO);

        if(producer.getName() == null)
            throw new NullValueException();

        Producer savedProducer = producerRepository.save(producer);

        ProducerDTO returnDTO = producerMapper.producerToProducerDTO(savedProducer);

        return returnDTO;
    }

    private void checkIfValueNotExsistInBase(ProducerDTO producerDTO) {
        List<Producer> categories = producerRepository.findAll();
        for (Producer producer : categories) {
            if (producer.getName().equals(producerDTO.getName()))
                throw new ValueExsistException();
        }
    }
}
