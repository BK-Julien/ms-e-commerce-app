package dev.bkjulien.product.product;

import dev.bkjulien.product.category.Category;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity @AllArgsConstructor @NoArgsConstructor @Builder @Getter @Setter
public class Product {
    @Id @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    private double availableQuantity;
    private BigDecimal price;
    @ManyToOne @JoinColumn(name = "category_id")
    private Category category;
}
