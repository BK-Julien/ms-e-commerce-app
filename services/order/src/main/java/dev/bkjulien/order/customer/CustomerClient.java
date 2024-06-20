package dev.bkjulien.order.customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "customer", url = "${application.config.customer-url}")
public interface CustomerClient {

    @GetMapping(path = "/{customer-id}")
    Optional<CustomerResponse> getCustomer(@PathVariable("customer-id") String CustomerId){

    }
}
