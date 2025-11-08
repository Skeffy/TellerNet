package io.github.skeffy.tellernet.dao;

import io.github.skeffy.tellernet.exception.DaoException;

import io.github.skeffy.tellernet.model.Customer;
import io.github.skeffy.tellernet.service.ProfileBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;

public class JdbcCustomerDaoTests extends BaseDaoTests{

    protected static final Customer CUSTOMER_1 = new Customer(1, "Noah", "Stevens", "555-555-5555", "123 Main St", "dontstealmymoney@gmail.com", LocalDate.of(1998, 7, 22));
    protected static final Customer CUSTOMER_2 = new Customer(2, "John", "Doe", "123-456-7890", "8290 4th Ave", "myemail@aol.com", LocalDate.of(1985, 11, 18));
    protected static final Customer UPDATE_CUSTOMER = new Customer(3, "Jane", "Martin", "649-280-1127", "23 Street Blvd", "plsnospam@yahoo.com", LocalDate.of(2000,4,9));
    protected static final Customer NEW_CUSTOMER = new Customer(6, "Tony", "Alvarez", "672-168-5432", "55 W Maple St", "email@email.email", LocalDate.of(1991,6,21));
    protected static final Customer DELETE_CUSTOMER = new Customer(4, "Joseph", "Bank", "518-348-3481", "505 Main St", "originalbanker@bing.com", LocalDate.of(1996,6,16));

    private JdbcCustomerDao dao;
    @Autowired
    private ProfileBuilder profileBuilder;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        dao = new JdbcCustomerDao(jdbcTemplate);
    }

    @Test
    public void getCustomers_retrieves_all_customers() {
        List<Customer> customers = dao.getCustomers();

        Assert.assertNotNull(customers);
        Assert.assertEquals(5, customers.size());
        assertCustomersMatch(CUSTOMER_1, customers.getFirst());
    }

    @Test
    public void getCustomerById_retrieves_customer_from_valid_id() {
        Customer customer = dao.getCustomerById(CUSTOMER_1.getCustomerId());

        Assert.assertNotNull(customer);
        assertCustomersMatch(CUSTOMER_1, customer);
    }

    @Test
    public void getCustomersByName_returns_list_of_customers_from_partial_first_name() {
        List<Customer> customers = dao.getCustomersByName("o", null);

        Assert.assertNotNull(customers);
        Assert.assertEquals(4, customers.size());
        assertCustomersMatch(CUSTOMER_1, customers.getFirst());
    }

    @Test
    public void getCustomersByName_returns_list_of_customers_from_partial_last_name() {
        List<Customer> customers = dao.getCustomersByName(null, "oe");

        Assert.assertNotNull(customers);
        Assert.assertEquals(2, customers.size());
        assertCustomersMatch(CUSTOMER_2, customers.getFirst());
    }

    @Test
    public void getCustomersByName_returns_list_of_customers_from_partial_first_and_last() {
        List<Customer> customers = dao.getCustomersByName("o", "s");

        Assert.assertNotNull(customers);
        Assert.assertEquals(2, customers.size());
        assertCustomersMatch(CUSTOMER_1, customers.getFirst());
    }

    @Test
    public void getCustomersByName_returns_all_customers_when_given_null_null() {
        List<Customer> customers = dao.getCustomersByName(null, null);

        Assert.assertNotNull(customers);
        Assert.assertEquals(5, customers.size());
        assertCustomersMatch(CUSTOMER_1, customers.getFirst());
    }

    @Test
    public void getCustomersByPhone_returns_list_of_customers_from_partial_phone() {
        List<Customer> customers = dao.getCustomersByPhone("45");

        Assert.assertNotNull(customers);
        Assert.assertEquals(2, customers.size());
        assertCustomersMatch(CUSTOMER_2, customers.getFirst());
    }

    @Test
    public void getCustomersByEmail_returns_list_of_customers_from_partial_email() {
        List<Customer> customers = dao.getCustomersByEmail("my");

        Assert.assertNotNull(customers);
        Assert.assertEquals(2, customers.size());
        assertCustomersMatch(CUSTOMER_1, customers.getFirst());
    }

    @Test
    public void getCustomersByDob_returns_list_of_customers_with_given_dob() {
        List<Customer> customers = dao.getCustomersByDob(LocalDate.of(1998,7,22));

        Assert.assertNotNull(customers);
        Assert.assertEquals(1, customers.size());
        assertCustomersMatch(CUSTOMER_1, customers.getFirst());
    }

    @Test
    public void createCustomer_adds_new_customer_to_db() {
        Customer customer = dao.createCustomer(NEW_CUSTOMER);

        Assert.assertNotNull(customer);
        assertCustomersMatch(NEW_CUSTOMER, customer);
    }

    @Test
    public void updateCustomer_updates_db_entry_for_given_customer() {
        int rowsAffected = dao.updateCustomer(UPDATE_CUSTOMER);
        Customer updatedCustomer = dao.getCustomerById(UPDATE_CUSTOMER.getCustomerId());

        Assert.assertNotNull(updatedCustomer);
        Assert.assertEquals(1, rowsAffected);
        assertCustomersMatch(UPDATE_CUSTOMER, updatedCustomer);
    }

    @Test
    public void deleteCustomer_throws_exception_for_customer_with_open_accounts() {
        Assert.assertThrows(DaoException.class, () -> {
            dao.deleteCustomer(profileBuilder.createProfileByCustomer(CUSTOMER_1.getCustomerId()));
        });
    }

    @Test
    public void deleteCustomer_removes_customer_with_no_accounts() {
        int rowsAffected = dao.deleteCustomer(profileBuilder.createProfileByCustomer(DELETE_CUSTOMER.getCustomerId()));
        Customer deletedCustomer = dao.getCustomerById(DELETE_CUSTOMER.getCustomerId());

        Assert.assertNull(deletedCustomer);
        Assert.assertEquals(1, rowsAffected);
    }

    private void assertCustomersMatch(Customer expected, Customer actual) {
        Assert.assertEquals("Customer ids do not match", expected.getCustomerId(), actual.getCustomerId());
        Assert.assertEquals("Customer first names do not match", expected.getFirstName(), actual.getFirstName());
        Assert.assertEquals("Customer last names do not match", expected.getLastName(), actual.getLastName());
        Assert.assertEquals("Customer phone numbers do not match", expected.getPhone(), actual.getPhone());
        Assert.assertEquals("Customer addresses do not match", expected.getAddress(), actual.getAddress());
        Assert.assertEquals("Customer emails do not match", expected.getEmail(), actual.getEmail());
        Assert.assertEquals("Customer dates of birth do not match", expected.getDob(), actual.getDob());
    }
}
