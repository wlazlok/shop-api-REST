package karol.spring.shopapi.services;

import karol.spring.shopapi.api.v1.models.ProductDTO;
import karol.spring.shopapi.api.v1.models.ProductDTOShortView;
import karol.spring.shopapi.models.Product;

import java.util.List;

public interface ProductService {
    ProductDTO getProductById(Long id);

    List<ProductDTOShortView> getAllProductShortView();

    ProductDTO createNewProduct(ProductDTO productDTO);

    void deleteById(Long id);

    ProductDTO updateProductById(Long id, ProductDTO productDTO);
}
