package karol.spring.shopapi.services;

import karol.spring.shopapi.api.v1.models.CategoryDTO;
import karol.spring.shopapi.api.v1.models.CategoryDTOShortView;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();

    CategoryDTO createNewCategory(CategoryDTO categoryDTO);

    void deleteById(Long id);

    CategoryDTO getCategoryById(Long id);

    CategoryDTO updateCategoryById(Long id, CategoryDTO categoryDTO);

    List<CategoryDTOShortView> getAllCategoriesShortView();
}
