package karol.spring.shopapi.boostrap;

import karol.spring.shopapi.models.Category;
import karol.spring.shopapi.repositories.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LoadData implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public LoadData(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("##### LOADING DATA #####");
        loadCategories();

    }

    private void loadCategories() {
        Category books = new Category();
        books.setName("Books");
        categoryRepository.save(books);

        Category magazines = new Category();
        magazines.setName("Magazines");
        categoryRepository.save(magazines);
    }
}
