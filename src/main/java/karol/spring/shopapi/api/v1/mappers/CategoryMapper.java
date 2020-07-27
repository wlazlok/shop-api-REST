package karol.spring.shopapi.api.v1.mappers;

import karol.spring.shopapi.api.v1.models.CategoryDTO;
import karol.spring.shopapi.api.v1.models.CategoryDTOShortView;
import karol.spring.shopapi.api.v1.models.ProductDTO;
import karol.spring.shopapi.api.v1.models.ProductDTOShortView;
import karol.spring.shopapi.models.Category;
import karol.spring.shopapi.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategorDTO(Category category);

    Category categoryDTOToCategoy(CategoryDTO categoryDTO);

    CategoryDTOShortView categoryToCategoryShorView(Category category);
}
