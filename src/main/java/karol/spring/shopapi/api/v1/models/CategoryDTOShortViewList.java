package karol.spring.shopapi.api.v1.models;

import java.util.List;

public class CategoryDTOShortViewList {

    List<CategoryDTOShortView> categoriesShort;

    public CategoryDTOShortViewList(List<CategoryDTOShortView> categoriesShort) {
        this.categoriesShort = categoriesShort;
    }

    public List<CategoryDTOShortView> getCategoriesShort() {
        return categoriesShort;
    }

    public void setCategoriesShort(List<CategoryDTOShortView> categoriesShort) {
        this.categoriesShort = categoriesShort;
    }
}
