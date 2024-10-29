package io.github.skeffy.tellernet.dao;

import io.github.skeffy.tellernet.model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

public class JdbcCustomerDao implements CustomerDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcCustomerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Customer> getCustomersByName(String firstName, String lastName) {
        return null;
    }

    @Override
    public List<Customer> getCustomersByPhone(String phone) {
        return null;
    }

    @Override
    public List<Customer> getCustomersByAddress(String address) {
        return null;
    }

    @Override
    public List<Customer> getCustomersByEmail(String email) {
        return null;
    }

    @Override
    public List<Customer> getCustomersByDob(Date dob) {
        return null;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return null;
    }

    @Override
    public int updateCustomer(Customer customer) {
        return 0;
    }

    @Override
    public int deleteCustomer(Customer customer) {
        return 0;
    }
}
