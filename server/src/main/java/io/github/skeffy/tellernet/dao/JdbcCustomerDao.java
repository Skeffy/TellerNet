package io.github.skeffy.tellernet.dao;

import io.github.skeffy.tellernet.exception.DaoException;
import io.github.skeffy.tellernet.model.Customer;
import io.github.skeffy.tellernet.model.Profile;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCustomerDao implements CustomerDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcCustomerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                customers.add(mapRowToCustomer(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return customers;
    }

    @Override
    public Customer getCustomerById(int id) {
        Customer customer = null;
        String sql = "SELECT * FROM customer WHERE customer_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                customer = mapRowToCustomer(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return customer;
    }

    @Override
    public List<Customer> getCustomersByName(String firstName, String lastName) {
        if (firstName == null) {
            firstName = "";
        }
        if (lastName == null) {
            lastName = "";
        }
        firstName = "%" + firstName + "%";
        lastName = "%" + lastName + "%";
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer WHERE first_name ILIKE ? AND last_name ILIKE ? ORDER BY first_name";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, firstName, lastName);
            while (results.next()) {
                customers.add(mapRowToCustomer(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return customers;
    }

    @Override
    public List<Customer> getCustomersByPhone(String phone) {
        phone = "%" + phone + "%";
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer WHERE phone ILIKE ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, phone);
            while (results.next()) {
                customers.add(mapRowToCustomer(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return customers;
    }

    @Override
    public List<Customer> getCustomersByAddress(String address) {
        address = "%" + address + "%";
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer WHERE address ILIKE ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, address);
            while (results.next()) {
                customers.add(mapRowToCustomer(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return customers;
    }

    @Override
    public List<Customer> getCustomersByEmail(String email) {
        email = "%" + email + "%";
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer WHERE email ILIKE ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, email);
            while (results.next()) {
                customers.add(mapRowToCustomer(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return customers;
    }

    @Override
    public List<Customer> getCustomersByDob(LocalDate dob) {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer WHERE CAST(dob AS DATE) = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, Date.valueOf(dob));
            while (results.next()) {
                customers.add(mapRowToCustomer(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return customers;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        Customer newCustomer;
        String sql = "INSERT INTO customer (first_name, last_name, phone, address, email, dob) VALUES (?, ?, ?, ?, ?, ?) RETURNING customer_id";
        try {
            int newId = jdbcTemplate.queryForObject(sql, int.class, customer.getFirstName(), customer.getLastName(),
                    customer.getPhone(), customer.getAddress(), customer.getEmail(), Date.valueOf(customer.getDob()));
            newCustomer = getCustomerById(newId);
        } catch (NullPointerException e) {
            throw new DaoException("Error adding entry to the database", e);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newCustomer;
    }

    @Override
    public int updateCustomer(Customer customer) {
        int rowsAffected;
        String sql = "UPDATE customer SET first_name = ?, last_name = ?, email = ?, address = ?, phone = ? WHERE customer_id = ?";
        try {
            rowsAffected = jdbcTemplate.update(sql, customer.getFirstName(), customer.getLastName(), customer.getEmail(),
                    customer.getAddress(), customer.getPhone(), customer.getCustomerId());
            if (rowsAffected == 0) {
                throw new DaoException("Zero rows affected. Expected at least one");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return rowsAffected;
    }

    @Override
    public int deleteCustomer(Profile profile) {
        int rowsAffected;
        String sql = "DELETE FROM customer WHERE customer_id = ?";
        if (!profile.hasAccount()) {
            try {
                rowsAffected = jdbcTemplate.update(sql, profile.getCustomer().getCustomerId());
                if (rowsAffected == 0) {
                    throw new DaoException("No content deleted");
                }
            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to server or database", e);
            } catch (DataIntegrityViolationException e) {
                throw new DaoException("Data integrity violation", e);
            }
            return rowsAffected;
        } else {
            throw new DaoException("Cannot delete customer with open accounts");
        }
    }

    private Customer mapRowToCustomer(SqlRowSet results) {
        Customer customer = new Customer();
        customer.setCustomerId(results.getInt("customer_id"));
        customer.setDob(results.getDate("dob").toLocalDate());
        customer.setAddress(results.getString("address"));
        customer.setEmail(results.getString("email"));
        customer.setPhone(results.getString("phone"));
        customer.setFirstName(results.getString("first_name"));
        customer.setLastName(results.getString("last_name"));
        return customer;
    }
}
