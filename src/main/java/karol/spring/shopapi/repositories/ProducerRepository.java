package karol.spring.shopapi.repositories;

import karol.spring.shopapi.models.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepository extends JpaRepository<Producer, Long> {
}
