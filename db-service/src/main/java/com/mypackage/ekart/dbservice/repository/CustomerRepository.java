package com.mypackage.ekart.dbservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mypackage.ekart.dbservice.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
