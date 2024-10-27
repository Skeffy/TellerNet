package io.github.skeffy.tellernet.model;

import java.util.Date;

public class Customer {

    private int customerId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String email;
    private Date dob;

    public Customer(int customerId, String firstName, String lastName, String phone, String address, String email, Date dob) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.dob = dob;
    }
}
