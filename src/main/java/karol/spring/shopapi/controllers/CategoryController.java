package karol.spring.shopapi.controllers;

import karol.spring.shopapi.api.v1.models.CategoryDTO;
import karol.spring.shopapi.api.v1.models.CategoryDTOList;
import karol.spring.shopapi.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/categories/")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTOList getAllCategories(){
        return new CategoryDTOList(categoryService.getAllCategories());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO createNewCategory(@RequestBody CategoryDTO categoryDTO){
        return categoryService.createNewCategory(categoryDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategoryById(@PathVariable Long id){
        categoryService.deleteById(id);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO){
        return categoryService.updateCategoryById(id, categoryDTO);
    }
}
