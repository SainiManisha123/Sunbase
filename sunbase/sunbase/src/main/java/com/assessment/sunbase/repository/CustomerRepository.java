package com.assessment.sunbase.repository;

import com.assessment.sunbase.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.SQLException;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT u FROM Customer u WHERE LOWER(u.email) = LOWER(:email)")
    public Customer getUserByEmail(@Param("email") String email) throws SQLException;
}

