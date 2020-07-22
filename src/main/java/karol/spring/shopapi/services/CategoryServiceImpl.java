package karol.spring.shopapi.services;

import karol.spring.shopapi.api.v1.mappers.CategoryMapper;
import karol.spring.shopapi.api.v1.models.CategoryDTO;
import karol.spring.shopapi.exceptions.NullValueException;
import karol.spring.shopapi.exceptions.ValueExsistException;
import karol.spring.shopapi.exceptions.ValueNotFoundException;
import karol.spring.shopapi.models.Category;
import karol.spring.shopapi.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::categoryToCategorDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO createNewCategory(CategoryDTO categoryDTO) {
        Category category = categoryMapper.categoryDTOToCategoy(categoryDTO);

        checkIfValueNotExsistInBase(categoryDTO);

        if(category.getName() == null)
            throw new NullValueException();

        Category savedCategory = categoryRepository.save(category);

        CategoryDTO returnDTO = categoryMapper.categoryToCategorDTO(savedCategory);

        return returnDTO;
    }

    private void checkIfValueNotExsistInBase(CategoryDTO categoryDTO) {
        List<Category> categories = categoryRepository.findAll();
        for (Category cat : categories) {
            if (cat.getName().equals(categoryDTO.getName()))
                throw new ValueExsistException();
        }
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::categoryToCategorDTO)
                .orElseThrow(ValueNotFoundException::new);
    }

    @Override
    public CategoryDTO updateCategoryById(Long id, CategoryDTO categoryDTO) {
        return categoryRepository.findById(id).map(category ->{

            checkIfValueNotExsistInBase(categoryDTO);
            if(categoryDTO.getName() != null)
                category.setName(categoryDTO.getName());

            return categoryMapper.categoryToCategorDTO(categoryRepository.save(category));

        }).orElseThrow(ValueNotFoundException::new);
    }

}
