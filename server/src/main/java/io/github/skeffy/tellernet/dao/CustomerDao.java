package io.github.skeffy.tellernet.dao;

import io.github.skeffy.tellernet.model.Customer;
import io.github.skeffy.tellernet.model.Profile;

import java.time.LocalDate;
import java.util.List;

public interface CustomerDao {

    List<Customer> getCustomers();
    Customer getCustomerById(int id);
    List<Customer> getCustomersByName(String firstName, String lastName);
    List<Customer> getCustomersByPhone(String phone);
    List<Customer> getCustomersByAddress(String address);
    List<Customer> getCustomersByEmail(String email);
    List<Customer> getCustomersByDob(LocalDate dob);
    Customer createCustomer(Customer customer);
    int updateCustomer(Customer customer);
    int deleteCustomer(Profile profile);
}
