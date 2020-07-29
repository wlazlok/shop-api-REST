package karol.spring.shopapi.api.v1.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import karol.spring.shopapi.api.v1.models.ProducerDTO;
import karol.spring.shopapi.api.v1.models.ProducerDTOShortView;
import karol.spring.shopapi.models.Producer;
import karol.spring.shopapi.models.Product;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-07-29T09:27:48+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.6 (AdoptOpenJDK)"
)
@Component
public class ProducerMapperImpl implements ProducerMapper {

    @Override
    public ProducerDTOShortView producerToDTOShort(Producer producer) {
        if ( producer == null ) {
            return null;
        }

        ProducerDTOShortView producerDTOShortView = new ProducerDTOShortView();

        producerDTOShortView.setId( producer.getId() );
        producerDTOShortView.setName( producer.getName() );

        return producerDTOShortView;
    }

    @Override
    public Producer producerDTOToProducer(ProducerDTO producerDTO) {
        if ( producerDTO == null ) {
            return null;
        }

        Producer producer = new Producer();

        producer.setId( producerDTO.getId() );
        producer.setName( producerDTO.getName() );
        List<Product> list = producerDTO.getProducts();
        if ( list != null ) {
            producer.setProducts( new ArrayList<Product>( list ) );
        }

        return producer;
    }

    @Override
    public ProducerDTO producerToProducerDTO(Producer producer) {
        if ( producer == null ) {
            return null;
        }

        ProducerDTO producerDTO = new ProducerDTO();

        producerDTO.setId( producer.getId() );
        producerDTO.setName( producer.getName() );
        List<Product> list = producer.getProducts();
        if ( list != null ) {
            producerDTO.setProducts( new ArrayList<Product>( list ) );
        }

        return producerDTO;
    }
}
