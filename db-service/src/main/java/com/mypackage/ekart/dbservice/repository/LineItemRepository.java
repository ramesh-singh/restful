package com.mypackage.ekart.dbservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mypackage.ekart.dbservice.model.LineItem;

public interface LineItemRepository extends JpaRepository<LineItem, Long>{

}
