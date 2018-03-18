package com.mypackage.ekart.dbservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mypackage.ekart.dbservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
