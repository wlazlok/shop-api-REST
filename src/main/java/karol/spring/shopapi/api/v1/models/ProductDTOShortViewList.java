package karol.spring.shopapi.api.v1.models;

import java.util.List;

public class ProductDTOShortViewList {
    List<ProductDTOShortView> productsShortView;

    public ProductDTOShortViewList(List<ProductDTOShortView> productsShortView) {
        this.productsShortView = productsShortView;
    }

    public List<ProductDTOShortView> getProductsShortView() {
        return productsShortView;
    }

    public void setProductsShortView(List<ProductDTOShortView> productsShortView) {
        this.productsShortView = productsShortView;
    }
}
