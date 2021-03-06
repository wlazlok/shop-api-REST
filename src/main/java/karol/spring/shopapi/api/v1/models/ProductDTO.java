package karol.spring.shopapi.api.v1.models;

import karol.spring.shopapi.models.Category;
import karol.spring.shopapi.models.Producer;
import karol.spring.shopapi.repositories.CategoryRepository;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;


public class ProductDTO {

    private Long id;
    private String name;
    private Double price;
    private String description;
    private Producer producer;
    private LocalDate producedDate;
    private LocalDate expiryDate;
    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public LocalDate getProducedDate() {
        return producedDate;
    }

    public void setProducedDate(LocalDate producedDate) {
        if(producedDate == null)
            this.producedDate = LocalDate.now();
        else if (producedDate != LocalDate.now())
            this.producedDate = producedDate;
        else
            this.producedDate = LocalDate.now();
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
