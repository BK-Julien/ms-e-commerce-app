package dev.bkjulien.order.orderline;

import dev.bkjulien.order.order.Order;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity @AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class OrderLine {
    @Id @GeneratedValue
    private Integer id;
    @ManyToOne @JoinColumn(name = "order_id")
    private Order order;
    private Integer productId;
    private double quantity;
}
