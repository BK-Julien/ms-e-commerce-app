package dev.bkjulien.product.category;

import dev.bkjulien.product.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity @AllArgsConstructor @NoArgsConstructor @Builder @Getter @Setter
public class Category {
    @Id @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Product> products;
}
