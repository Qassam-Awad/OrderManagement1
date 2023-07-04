package com.example.OrderManagement1.service;

import com.example.OrderManagement1.entity.Customer;
import com.example.OrderManagement1.payload.CustomerDto;

import java.time.LocalDate;
import java.util.List;

/**
 * The CustomerService interface provides methods for managing customers.
 */
public interface CustomerService {

    /**
     * Retrieves a list of all customers.
     *
     * @return A list of CustomerDto objects representing all customers.
     */
    List<CustomerDto> getAllCustomers();

    /**
     * Retrieves a customer by their ID.
     *
     * @param customerId The ID of the customer to retrieve.
     * @return The CustomerDto object representing the customer.
     */
    CustomerDto getCustomerById(int customerId);

    /**
     * Creates a new customer.
     *
     * @param customerDto The CustomerDto object representing the customer to create.
     * @return The CustomerDto object representing the created customer.
     */
    CustomerDto createCustomer(CustomerDto customerDto);

    /**
     * Updates an existing customer.
     *
     * @param customerId  The ID of the customer to update.
     * @param customerDto The CustomerDto object representing the updated customer data.
     * @return The CustomerDto object representing the updated customer.
     */
    CustomerDto updateCustomer(int customerId, CustomerDto customerDto);

    /**
     * Deletes a customer.
     *
     * @param customerId The ID of the customer to delete.
     */
    void deleteCustomer(int customerId);

    /**
     * Retrieves a list of customers whose birthdate is before the specified date.
     *
     * @param date The date to compare birthdates with.
     * @return A list of CustomerDto objects representing the customers.
     */
    List<CustomerDto> getCustomersByBirthdateBefore(LocalDate date);

    /**
     * Retrieves a list of customers by their first name.
     *
     * @param firstName The first name to search for.
     * @return A list of CustomerDto objects representing the customers.
     */
    List<CustomerDto> getCustomersByFirstName(String firstName);

    /**
     * Retrieves a list of customers by their last name.
     *
     * @param lastName The last name to search for.
     * @return A list of CustomerDto objects representing the customers.
     */
    List<CustomerDto> getCustomersByLastName(String lastName);

    /**
     * Retrieves a list of customers by their birthdate.
     *
     * @param birthdate The birthdate to search for.
     * @return A list of CustomerDto objects representing the customers.
     */
    List<CustomerDto> getCustomersByBirthdate(LocalDate birthdate);

    /**
     * Retrieves a list of customers by their first name and last name.
     *
     * @param firstName The first name to search for.
     * @param lastName  The last name to search for.
     * @return A list of CustomerDto objects representing the customers.
     */
    List<CustomerDto> getCustomersByFirstNameAndLastName(String firstName, String lastName);

    /**
     * Retrieves a list of customers whose birthdate falls within the specified date range.
     *
     * @param startDate The start date of the range.
     * @param endDate   The end date of the range.
     * @return A list of CustomerDto objects representing the customers.
     */
    List<CustomerDto> getCustomersByBirthdateRange(LocalDate startDate, LocalDate endDate);

    /**
     * Registers a new customer.
     *
     * @param customer The Customer object representing the customer to register.
     */
    void registerCustomer(Customer customer);

    /**
     * Checks if an email is already registered for a customer.
     *
     * @param email The email to check.
     * @return true if the email is already registered, false otherwise.
     */
    boolean isEmailRegistered(String email);
}
