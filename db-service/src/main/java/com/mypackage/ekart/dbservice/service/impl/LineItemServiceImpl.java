package com.mypackage.ekart.dbservice.service.impl;

import org.springframework.stereotype.Component;

import com.mypackage.ekart.dbservice.model.LineItem;
import com.mypackage.ekart.dbservice.repository.LineItemRepository;
import com.mypackage.ekart.dbservice.service.LineItemService;

@Component
public class LineItemServiceImpl implements LineItemService {
	private LineItemRepository repository;

	public LineItemServiceImpl(LineItemRepository repository) {
		this.repository= repository;
	}

	@Override
	public void addLineItem(LineItem lineItem) {
		repository.save(lineItem);
		
	}

}
