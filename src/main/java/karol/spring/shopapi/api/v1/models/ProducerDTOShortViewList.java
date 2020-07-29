package karol.spring.shopapi.api.v1.models;

import java.util.ArrayList;
import java.util.List;

public class ProducerDTOShortViewList {

    List<ProducerDTOShortView> producers;

    public ProducerDTOShortViewList(List<ProducerDTOShortView> producers) {
        this.producers = producers;
    }

    public List<ProducerDTOShortView> getProducers() {
        return producers;
    }

    public void setProducers(List<ProducerDTOShortView> producers) {
        this.producers = producers;
    }
}
