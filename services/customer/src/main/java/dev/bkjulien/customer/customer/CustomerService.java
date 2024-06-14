package dev.bkjulien.customer.customer;

import java.util.List;

public interface CustomerService {
    String createCustomer(CustomerRequest request);

    void updateCustomer(CustomerRequest request);

    List<CustomerResponse> getCustomers();

    Boolean existsById(String customerId);

    CustomerResponse getCustomer(String customerId);

    void deleteCustomer(String customerId);
}
