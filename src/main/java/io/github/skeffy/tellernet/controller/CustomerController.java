package io.github.skeffy.tellernet.controller;

import io.github.skeffy.tellernet.dao.CustomerDao;
import io.github.skeffy.tellernet.exception.DaoException;
import io.github.skeffy.tellernet.model.Customer;
import io.github.skeffy.tellernet.service.ProfileBuilder;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerDao customerDao;
    private ProfileBuilder profileBuilder;

    public CustomerController(CustomerDao customerDao, ProfileBuilder profileBuilder) {
        this.customerDao = customerDao;
        this.profileBuilder = profileBuilder;
    }

    @GetMapping
    public List<Customer> getCustomers(Customer customer) {
        try {
            if (customer.getFirstName() == null) {
                return customerDao.getCustomers();
            } else if (!customer.getFirstName().isEmpty() || !customer.getLastName().isEmpty()) {
                return customerDao.getCustomersByName(customer.getFirstName(), customer.getLastName());
            } else if (customer.getDob() != null) {
                return customerDao.getCustomersByDob(customer.getDob());
            } else if (!customer.getEmail().isEmpty()) {
                return customerDao.getCustomersByEmail(customer.getEmail());
            } else if (!customer.getAddress().isEmpty()) {
                return customerDao.getCustomersByAddress(customer.getAddress());
            } else if (!customer.getPhone().isEmpty()) {
                return customerDao.getCustomersByPhone(customer.getPhone());
            } else {
                return customerDao.getCustomers();
            }
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }

    @GetMapping
    public List<Customer> getAllCustomer() {
        try {
            return customerDao.getCustomers();
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody @Valid Customer customer) {
        try {
            return customerDao.createCustomer(customer);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }

    @PutMapping
    public void updateCustomer(@RequestBody Customer customer) {
        try {
            customerDao.updateCustomer(customer);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@RequestBody int customerId) {
        try {
            customerDao.deleteCustomer(profileBuilder.createProfileByCustomer(customerId));
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }
}
