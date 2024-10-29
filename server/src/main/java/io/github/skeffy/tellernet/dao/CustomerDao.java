package io.github.skeffy.tellernet.dao;

import io.github.skeffy.tellernet.model.Customer;

import java.util.Date;
import java.util.List;

public interface CustomerDao {

    List<Customer> getCustomersByName(String firstName, String lastName);
    List<Customer> getCustomersByPhone(String phone);
    List<Customer> getCustomersByAddress(String address);
    List<Customer> getCustomersByEmail(String email);
    List<Customer> getCustomersByDob(Date dob);
    Customer createCustomer(Customer customer);
    int updateCustomer(Customer customer);
    int deleteCustomer(Customer customer);
}
