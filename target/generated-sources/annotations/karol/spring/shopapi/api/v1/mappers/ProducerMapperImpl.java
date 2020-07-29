package karol.spring.shopapi.api.v1.mappers;

import javax.annotation.Generated;
import karol.spring.shopapi.api.v1.models.ProducerDTOShortView;
import karol.spring.shopapi.models.Producer;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-07-29T09:20:18+0200",
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
}
