package karol.spring.shopapi.api.v1.mappers;

import karol.spring.shopapi.api.v1.models.CategoryDTO;
import karol.spring.shopapi.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategorDTO(Category category);

    Category categoryDTOToCategoy(CategoryDTO categoryDTO);
}
