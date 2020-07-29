package karol.spring.shopapi.api.v1.mappers;

import javax.annotation.Generated;
import karol.spring.shopapi.api.v1.models.ProductDTO;
import karol.spring.shopapi.api.v1.models.ProductDTOShortView;
import karol.spring.shopapi.models.Product;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-07-29T09:24:42+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.6 (AdoptOpenJDK)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDTO productToProductDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setId( product.getId() );
        productDTO.setName( product.getName() );
        productDTO.setPrice( product.getPrice() );
        productDTO.setDescription( product.getDescription() );
        productDTO.setProducer( product.getProducer() );
        productDTO.setProducedDate( product.getProducedDate() );
        productDTO.setExpiryDate( product.getExpiryDate() );
        productDTO.setCategory( product.getCategory() );

        return productDTO;
    }

    @Override
    public Product productDTOToProduct(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( productDTO.getId() );
        product.setName( productDTO.getName() );
        product.setPrice( productDTO.getPrice() );
        product.setDescription( productDTO.getDescription() );
        product.setProducedDate( productDTO.getProducedDate() );
        product.setExpiryDate( productDTO.getExpiryDate() );
        product.setCategory( productDTO.getCategory() );
        product.setProducer( productDTO.getProducer() );

        return product;
    }

    @Override
    public ProductDTOShortView productToProductShorView(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTOShortView productDTOShortView = new ProductDTOShortView();

        if ( product.getId() != null ) {
            productDTOShortView.setId( String.valueOf( product.getId() ) );
        }
        productDTOShortView.setName( product.getName() );

        return productDTOShortView;
    }
}
