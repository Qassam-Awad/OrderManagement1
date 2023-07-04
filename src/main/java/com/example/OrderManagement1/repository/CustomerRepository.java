package com.example.OrderManagement1.repository;

import com.example.OrderManagement1.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * The CustomerRepository interface is responsible for performing database operations related to the Customer entity.
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByBornAtBefore(LocalDate date);
    List<Customer> findByLastName(String lastName);
    List<Customer> findByFirstNameAndLastName(String firstName, String lastName);
    List<Customer> findByFirstName(String name);

    List<Customer> findByBornAt(LocalDate date);

    List<Customer> findByBornAtBetween(LocalDate startDate, LocalDate endDate);

    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByUsernameOrEmail(String username, String email);
    Optional<Customer> findByUsername(String username);
    Boolean existsByFirstName(String firstName);
    Boolean existsByEmail(String email);
}

