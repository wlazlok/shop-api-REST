package karol.spring.shopapi.api.v1.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import karol.spring.shopapi.api.v1.models.CategoryDTO;
import karol.spring.shopapi.api.v1.models.CategoryDTOShortView;
import karol.spring.shopapi.models.Category;
import karol.spring.shopapi.models.Product;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-07-27T10:45:37+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.6 (AdoptOpenJDK)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDTO categoryToCategorDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId( category.getId() );
        categoryDTO.setName( category.getName() );
        List<Product> list = category.getProducts();
        if ( list != null ) {
            categoryDTO.setProducts( new ArrayList<Product>( list ) );
        }

        return categoryDTO;
    }

    @Override
    public Category categoryDTOToCategoy(CategoryDTO categoryDTO) {
        if ( categoryDTO == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryDTO.getId() );
        category.setName( categoryDTO.getName() );
        List<Product> list = categoryDTO.getProducts();
        if ( list != null ) {
            category.setProducts( new ArrayList<Product>( list ) );
        }

        return category;
    }

    @Override
    public CategoryDTOShortView categoryToCategoryShorView(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDTOShortView categoryDTOShortView = new CategoryDTOShortView();

        categoryDTOShortView.setId( category.getId() );
        categoryDTOShortView.setName( category.getName() );

        return categoryDTOShortView;
    }
}
