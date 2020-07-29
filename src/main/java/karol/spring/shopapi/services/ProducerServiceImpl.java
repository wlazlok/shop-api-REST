package karol.spring.shopapi.services;

import karol.spring.shopapi.api.v1.mappers.ProducerMapper;
import karol.spring.shopapi.api.v1.models.ProducerDTO;
import karol.spring.shopapi.api.v1.models.ProducerDTOShortView;
import karol.spring.shopapi.exceptions.NullValueException;
import karol.spring.shopapi.exceptions.ValueExsistException;
import karol.spring.shopapi.exceptions.ValueNotFoundException;
import karol.spring.shopapi.models.Producer;
import karol.spring.shopapi.models.Product;
import karol.spring.shopapi.repositories.ProducerRepository;
import karol.spring.shopapi.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProducerServiceImpl implements ProducerService {

    private final ProducerRepository producerRepository;
    private final ProducerMapper producerMapper;
    protected final ProductRepository productRepository;

    public ProducerServiceImpl(ProducerRepository producerRepository, ProducerMapper producerMapper, ProductRepository productRepository) {
        this.producerRepository = producerRepository;
        this.producerMapper = producerMapper;
        this.productRepository = productRepository;
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

    @Override
    public void deleteProducerById(Long id) {
        Producer producer = producerRepository.findById(id).get();

        for (Product prod: producer.getProducts()) {
            prod.setProducer(null);
            productRepository.save(prod);
        }

        producer.getProducts().clear();
        producerRepository.save(producer);

        producerRepository.deleteById(id);
    }

    @Override
    public ProducerDTO updateProducer(Long id, ProducerDTO producerDTO) {

        return producerRepository.findById(id).map(producer ->{

            if(producerDTO.getName() != null)
                producer.setName(producerDTO.getName());

            return producerMapper.producerToProducerDTO(producerRepository.save(producer));

        }).orElseThrow(ValueNotFoundException::new);

    }

    private void checkIfValueNotExsistInBase(ProducerDTO producerDTO) {
        List<Producer> categories = producerRepository.findAll();
        for (Producer producer : categories) {
            if (producer.getName().equals(producerDTO.getName()))
                throw new ValueExsistException();
        }
    }
}
