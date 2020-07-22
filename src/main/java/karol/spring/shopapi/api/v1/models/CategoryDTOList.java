package karol.spring.shopapi.api.v1.models;

import java.util.List;

public class CategoryDTOList {

    List<CategoryDTO> categories;

    public CategoryDTOList(List<CategoryDTO> categories) {
        this.categories = categories;
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDTO> categories) {
        this.categories = categories;
    }
}
