package karol.spring.shopapi.services;

import karol.spring.shopapi.api.v1.mappers.ProductMapper;
import karol.spring.shopapi.api.v1.models.CategoryDTO;
import karol.spring.shopapi.api.v1.models.ProductDTO;
import karol.spring.shopapi.api.v1.models.ProductDTOShortView;
import karol.spring.shopapi.exceptions.ValueExsistException;
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

        Product product = productMapper.productDTOToProduct(productDTO);

        List<Category> categories = categoryRepository.findAll();

        for (Category cat: categories) {
            if(cat.getName().equals(product.getCategory().getName())) {
                product.setCategory(cat);

                Product savedProduct = productRepository.save(product);

                ProductDTO returnDTO = productMapper.productToProductDTO(savedProduct);

                return returnDTO;
            }
        }

        Category catToSave = new Category();

        catToSave.setName(product.getCategory().getName());

        Category savedCategory = categoryRepository.save(catToSave);

        product.setCategory(savedCategory);

        Product savedProduct = productRepository.save(product);

        ProductDTO returnDTO = productMapper.productToProductDTO(savedProduct);

        return returnDTO;
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductDTO updateProductById(Long id, ProductDTO productDTO) {

        List<Category> categories = categoryRepository.findAll();

        for (Category cat: categories) {
            if(cat.getName().equals(productDTO.getCategory().getName())){
                productDTO.setCategory(cat);
            }
        }

        return productRepository.findById(id).map(product ->{

            checkIfValueNotExsistInBase(productDTO);

            if(productDTO.getName() != null)
                product.setName(productDTO.getName());
            if(productDTO.getPrice() != null)
                product.setPrice(productDTO.getPrice());
            if(productDTO.getDescription() != null)
                product.setDescription(productDTO.getDescription());
            if(productDTO.getProducer() != null)
                product.setProducer(productDTO.getProducer());
            if(productDTO.getExpiryDate() != null)
                product.setExpiryDate(productDTO.getExpiryDate());
            if(productDTO.getProducedDate() != null)
                product.setProducedDate(productDTO.getProducedDate());
            System.out.println(productDTO.getCategory().getName());
            if(productDTO.getCategory() != null)
                product.setCategory(productDTO.getCategory());

            return productMapper.productToProductDTO(productRepository.save(product));

        }).orElseThrow(ValueNotFoundException::new);
    }

    private void checkIfValueNotExsistInBase(ProductDTO productDTO) {
        List<Product> categories = productRepository.findAll();
        for (Product prod : categories) {
            if (prod.getName().equals(productDTO.getName()))
                throw new ValueExsistException();
        }
    }
}
