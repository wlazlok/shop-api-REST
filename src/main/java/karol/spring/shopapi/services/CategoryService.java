package karol.spring.shopapi.services;

import karol.spring.shopapi.api.v1.models.CategoryDTO;
import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();

    CategoryDTO createNewCategory(CategoryDTO categoryDTO);

    void deleteById(Long id);

    CategoryDTO getCategoryById(Long id);
}
