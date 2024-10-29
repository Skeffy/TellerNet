package io.github.skeffy.tellernet.dao;

import io.github.skeffy.tellernet.model.Customer;

import java.util.Date;
import java.util.List;

public class JdbcCustomerDao implements CustomerDao{
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
}
