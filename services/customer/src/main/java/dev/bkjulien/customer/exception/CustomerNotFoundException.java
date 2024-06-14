package dev.bkjulien.customer.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true) @Data
public class CustomerNotFoundException extends RuntimeException{
    private final String msg;
}
