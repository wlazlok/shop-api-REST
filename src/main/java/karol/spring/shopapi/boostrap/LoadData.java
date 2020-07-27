package karol.spring.shopapi.boostrap;

import karol.spring.shopapi.models.Category;
import karol.spring.shopapi.models.Product;
import karol.spring.shopapi.repositories.CategoryRepository;
import karol.spring.shopapi.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class LoadData implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public LoadData(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
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

        Product niceBook = new Product();
        niceBook.setName("Harry Potter");
        niceBook.setPrice(15.64);
        niceBook.setDescription("Very interesting book");
        niceBook.setProducedDate(LocalDate.now());
        niceBook.setCategory(books);
        niceBook.setCategory(books);
        books.getProducts().add(niceBook);
        categoryRepository.save(magazines);
        productRepository.save(niceBook);

        System.out.println("List size: " + books.getProducts().size());
        System.out.println("Cateogry: " + niceBook.getCategory());
    }
}
