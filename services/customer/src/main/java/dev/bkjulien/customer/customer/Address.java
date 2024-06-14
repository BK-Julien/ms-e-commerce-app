package dev.bkjulien.customer.customer;

import lombok.*;
import org.springframework.validation.annotation.Validated;

@Validated @NoArgsConstructor @AllArgsConstructor @Builder @Getter @Setter
public class Address {
    private String street;
    private String houseNumber;
    private String zipCode;
}
