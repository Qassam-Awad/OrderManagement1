package com.example.OrderManagement1.controller;

import com.example.OrderManagement1.exception.ErrorResponse;
import com.example.OrderManagement1.exception.ResourceNotFoundException;
import com.example.OrderManagement1.payload.CustomerDto;
import com.example.OrderManagement1.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Api(tags = "Customer Management")
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomerController(CustomerService customerService, ModelMapper modelMapper) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    /**
     * Get all customers.
     *
     * @return List of CustomerDto objects
     */
    @GetMapping
    @ApiOperation("Get all customers")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    /**
     * Get a customer by ID.
     *
     * @param customerId ID of the customer
     * @return CustomerDto object
     */
    @GetMapping("/{customerId}")
    @ApiOperation("Get a customer by ID")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable int customerId) {
        CustomerDto customer = customerService.getCustomerById(customerId);
        return ResponseEntity.ok(customer);
    }

    /**
     * Create a new customer.
     *
     * @param customerDto CustomerDto object
     * @return Created CustomerDto object
     */
    @PostMapping
    @ApiOperation("Create a new customer")
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        CustomerDto createdCustomer = customerService.createCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    /**
     * Update an existing customer.
     *
     * @param customerId  ID of the customer
     * @param customerDto CustomerDto object
     * @return Updated CustomerDto object
     */
    @PutMapping("/{customerId}")
    @ApiOperation("Update an existing customer")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable int customerId, @Valid @RequestBody CustomerDto customerDto) {
        CustomerDto updatedCustomer = customerService.updateCustomer(customerId, customerDto);
        return ResponseEntity.ok(updatedCustomer);
    }

    /**
     * Delete a customer.
     *
     * @param customerId ID of the customer
     * @return No content
     */
    @DeleteMapping("/{customerId}")
    @ApiOperation("Delete a customer")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Exception handler for handling resource not found exception.
     *
     * @param ex ResourceNotFoundException object
     * @return ErrorResponse object with error details
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ApiOperation("Exception handler for handling resource not found exception")
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    /**
     * Get customers by first name and last name.
     *
     * @param firstName First name of the customers
     * @param lastName  Last name of the customers
     * @return List of CustomerDto objects
     */
    @GetMapping("/byName")
    @ApiOperation("Get customers by first name and last name")
    public ResponseEntity<List<CustomerDto>> getCustomersByFirstNameAndLastName(@RequestParam String firstName, @RequestParam String lastName) {
        List<CustomerDto> customers = customerService.getCustomersByFirstNameAndLastName(firstName, lastName);
        return ResponseEntity.ok(customers);
    }

    /**
     * Get customers by birthdate range.
     *
     * @param startDate Start date of the birthdate range
     * @param endDate   End date of the birthdate range
     * @return List of CustomerDto objects
     */
    @GetMapping("/byBirthdateRange")
    @ApiOperation("Get customers by birthdate range")
    public ResponseEntity<List<CustomerDto>> getCustomersByBirthdateRange(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                                          @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        List<CustomerDto> customers = customerService.getCustomersByBirthdateRange(startDate, endDate);
        return ResponseEntity.ok(customers);
    }

    /**
     * Get customers with birthdate before the specified date.
     *
     * @param date The date to compare birthdate with
     * @return List of CustomerDto objects
     */
    @GetMapping("/birthdate-before/{date}")
    public ResponseEntity<List<CustomerDto>> getCustomersByBirthdateBefore(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<CustomerDto> customers = customerService.getCustomersByBirthdateBefore(date);
        return ResponseEntity.ok(customers);
    }

    /**
     * Get customers by first name.
     *
     * @param firstName First name of the customers
     * @return List of CustomerDto objects
     */
    @GetMapping("/first-name/{firstName}")
    @ApiOperation("Get customers with birthdate before the specified date")
    public ResponseEntity<List<CustomerDto>> getCustomersByFirstName(@PathVariable String firstName) {
        List<CustomerDto> customers = customerService.getCustomersByFirstName(firstName);
        return ResponseEntity.ok(customers);
    }

    /**
     * Get customers by last name.
     *
     * @param lastName Last name of the customers
     * @return List of CustomerDto objects
     */
    @GetMapping("/last-name/{lastName}")
    @ApiOperation("Get customers by first name")
    public ResponseEntity<List<CustomerDto>> getCustomersByLastName(@PathVariable String lastName) {
        List<CustomerDto> customers = customerService.getCustomersByLastName(lastName);
        return ResponseEntity.ok(customers);
    }

    /**
     * Get customers by birthdate.
     *
     * @param date Birthdate of the customers
     * @return List of CustomerDto objects
     */
    @GetMapping("/birthdate/{date}")
    @ApiOperation("Get customers by birthdate")
    public ResponseEntity<List<CustomerDto>> getCustomersByBirthdate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<CustomerDto> customers = customerService.getCustomersByBirthdate(date);
        return ResponseEntity.ok(customers);
    }

}
