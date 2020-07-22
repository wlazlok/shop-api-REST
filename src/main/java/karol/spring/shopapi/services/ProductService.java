package karol.spring.shopapi.services;

import karol.spring.shopapi.api.v1.models.ProductDTO;
import karol.spring.shopapi.api.v1.models.ProductDTOShortView;

import java.util.List;

public interface ProductService {
    ProductDTO getProductById(Long id);

    List<ProductDTOShortView> getAllProductShortView();
}
