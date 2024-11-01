package io.github.skeffy.tellernet.controller;

import io.github.skeffy.tellernet.dao.CustomerDao;
import io.github.skeffy.tellernet.exception.DaoException;
import io.github.skeffy.tellernet.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerDao customerDao;

    public CustomerController(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @GetMapping
    public List<Customer> getCustomers(@RequestBody Customer customer) {
        try {
            if (customer.getFirstName() != null || customer.getLastName() != null) {
                return customerDao.getCustomersByName(customer.getFirstName(), customer.getLastName());
            } else if (customer.getDob() != null) {
                return customerDao.getCustomersByDob(customer.getDob());
            } else if (customer.getEmail() != null) {
                return customerDao.getCustomersByEmail(customer.getEmail());
            } else if (customer.getAddress() != null) {
                return customerDao.getCustomersByAddress(customer.getAddress());
            } else if (customer.getPhone() != null) {
                return customerDao.getCustomersByPhone(customer.getPhone());
            } else {
                return customerDao.getCustomers();
            }
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }
}
