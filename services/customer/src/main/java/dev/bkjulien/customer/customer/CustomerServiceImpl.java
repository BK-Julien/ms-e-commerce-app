package dev.bkjulien.customer.customer;


import dev.bkjulien.customer.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service @RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public String createCustomer(CustomerRequest request) {
        var customer = customerRepository.save(customerMapper.toCustomer(request));
        return customer.getId();
    }

    @Override
    public void updateCustomer(CustomerRequest request) {
        var customer = customerRepository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("Cannot update customer :: No customer found with the provided ID :: %s", request.id())
                ));

        mergerCustomer(customer, request);
        customerRepository.save(customer);
    }

    @Override
    public List<CustomerResponse> getCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::fromCustomer)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean existsById(String customerId) {
        return customerRepository.findById(customerId).isPresent();
    }

    @Override
    public CustomerResponse getCustomer(String customerId) {
        return customerRepository.findById(customerId)
                .map(customerMapper::fromCustomer)
                .orElseThrow(()-> new CustomerNotFoundException(
                        format("No customer found with the provided ID:: %s", customerId)
                ));
    }

    @Override
    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }

    private void mergerCustomer(Customer customer, CustomerRequest request) {
        if (StringUtils.isNotBlank(request.firstname()))
            customer.setFirstname(request.firstname());
        if (StringUtils.isNotBlank(request.lastname()))
            customer.setLastname(request.lastname());
        if (StringUtils.isNotBlank(request.email()))
            customer.setEmail(request.email());
        if(request.address() != null)
            customer.setAddress(request.address());
    }
}
