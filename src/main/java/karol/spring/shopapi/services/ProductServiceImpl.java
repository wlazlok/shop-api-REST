package karol.spring.shopapi.services;

import karol.spring.shopapi.api.v1.mappers.ProductMapper;
import karol.spring.shopapi.api.v1.models.ProductDTO;
import karol.spring.shopapi.api.v1.models.ProductDTOShortView;
import karol.spring.shopapi.exceptions.ValueNotFoundException;
import karol.spring.shopapi.models.Category;
import karol.spring.shopapi.models.Product;
import karol.spring.shopapi.repositories.CategoryRepository;
import karol.spring.shopapi.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public ProductDTO getProductById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::productToProductDTO)
                .orElseThrow(ValueNotFoundException::new);
    }

    @Override
    public List<ProductDTOShortView> getAllProductShortView() {
        return productRepository.findAll().stream()
                .map(productMapper::productToProductShorView)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO createNewProduct(ProductDTO productDTO) {
        //todo Dokonczyc tworzenie nowego produktu (blad z kategoria !)  !!!
        Product product = productMapper.productDTOToProduct(productDTO);

        Product savedProduct = productRepository.save(product);

        ProductDTO returnDTO = productMapper.productToProductDTO(savedProduct);

        return returnDTO;
    }
}
