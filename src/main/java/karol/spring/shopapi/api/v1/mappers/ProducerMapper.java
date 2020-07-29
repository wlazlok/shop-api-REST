package karol.spring.shopapi.api.v1.mappers;

import karol.spring.shopapi.api.v1.models.ProducerDTO;
import karol.spring.shopapi.api.v1.models.ProducerDTOShortView;
import karol.spring.shopapi.models.Producer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProducerMapper {

    ProducerMapper INSTANCE = Mappers.getMapper(ProducerMapper.class);

    ProducerDTOShortView producerToDTOShort(Producer producer);

    Producer producerDTOToProducer(ProducerDTO producerDTO);

    ProducerDTO producerToProducerDTO(Producer producer);
}
