package com.example.OrderManagement1.service.impl;

import com.example.OrderManagement1.entity.Customer;
import com.example.OrderManagement1.exception.ResourceNotFoundException;
import com.example.OrderManagement1.payload.CustomerDto;
import com.example.OrderManagement1.repository.CustomerRepository;
import com.example.OrderManagement1.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The CustomerServiceImpl class implements the CustomerService interface
 * to provide CRUD operations and custom queries for managing customers.
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    /**
     * Constructs a new CustomerServiceImpl with the given dependencies.
     *
     * @param customerRepository The customer repository to interact with the database.
     * @param modelMapper        The model mapper for converting between entities and DTOs.
     */
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto getCustomerById(int customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
        return convertToDto(customer);
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = convertToEntity(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        return convertToDto(savedCustomer);
    }

    @Override
    public CustomerDto updateCustomer(int customerId, CustomerDto customerDto) {
        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));

        existingCustomer.setFirstName(customerDto.getFirstName());
        existingCustomer.setLastName(customerDto.getLastName());
        existingCustomer.setBornAt(customerDto.getBornAt());

        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return convertToDto(updatedCustomer);
    }

    @Override
    public void deleteCustomer(int customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new ResourceNotFoundException("Customer", "id", customerId);
        }
        customerRepository.deleteById(customerId);
    }

    @Override
    public List<CustomerDto> getCustomersByFirstName(String firstName) {
        List<Customer> customers = customerRepository.findByFirstName(firstName);
        return customers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerDto> getCustomersByLastName(String lastName) {
        List<Customer> customers = customerRepository.findByLastName(lastName);
        return customers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerDto> getCustomersByBirthdate(LocalDate birthdate) {
        List<Customer> customers = customerRepository.findByBornAt(birthdate);
        return customers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerDto> getCustomersByFirstNameAndLastName(String firstName, String lastName) {
        List<Customer> customers = customerRepository.findByFirstNameAndLastName(firstName, lastName);
        return customers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void registerCustomer(Customer customer) {
        // Perform necessary steps to register the customer (e.g., save to the database)
        customerRepository.save(customer);
    }

    @Override
    public boolean isEmailRegistered(String email) {
        // Check if the email is already registered in the database
        return customerRepository.existsByEmail(email);
    }

    @Override
    public List<CustomerDto> getCustomersByBirthdateBefore(LocalDate date) {
        List<Customer> customers = customerRepository.findByBornAtBefore(date);
        return customers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerDto> getCustomersByBirthdateRange(LocalDate startDate, LocalDate endDate) {
        List<Customer> customers = customerRepository.findByBornAtBetween(startDate, endDate);
        return customers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private CustomerDto convertToDto(Customer customer) {
        return modelMapper.map(customer, CustomerDto.class);
    }

    private Customer convertToEntity(CustomerDto customerDto) {
        return modelMapper.map(customerDto, Customer.class);
    }
}
