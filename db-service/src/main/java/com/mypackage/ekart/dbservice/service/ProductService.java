package com.mypackage.ekart.dbservice.service;

import java.util.List;

import com.mypackage.ekart.dbservice.dto.ProductDTO;
import com.mypackage.ekart.dbservice.model.Product;

public interface ProductService {
	ProductDTO addProduct(Product product);
	List<ProductDTO> getAllProducts();
	ProductDTO getProductById(Long productId);
	void updateProduct(Product product, Long productId);
	void deleteProduct(Long productId);

}
