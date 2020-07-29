package karol.spring.shopapi.controllers;

import karol.spring.shopapi.api.v1.models.ProductDTO;
import karol.spring.shopapi.api.v1.models.ProductDTOShortViewList;
import karol.spring.shopapi.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product/")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ProductDTOShortViewList getAllProductsShortView(){
        return new ProductDTOShortViewList(productService.getAllProductShortView());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO createNewProduct(@RequestBody ProductDTO productDTO){
        return productService.createNewProduct(productDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProductById(@PathVariable Long id){
        productService.deleteById(id);
    }
}
