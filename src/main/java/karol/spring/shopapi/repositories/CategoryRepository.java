package karol.spring.shopapi.repositories;

import karol.spring.shopapi.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
