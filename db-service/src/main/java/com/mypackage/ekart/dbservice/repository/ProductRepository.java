package com.mypackage.ekart.dbservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mypackage.ekart.dbservice.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
