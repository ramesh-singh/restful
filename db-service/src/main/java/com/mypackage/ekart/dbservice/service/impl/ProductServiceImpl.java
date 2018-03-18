package com.mypackage.ekart.dbservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mypackage.ekart.dbservice.dto.ProductDTO;
import com.mypackage.ekart.dbservice.model.Product;
import com.mypackage.ekart.dbservice.repository.ProductRepository;
import com.mypackage.ekart.dbservice.service.ProductService;
import com.mypackage.ekart.dbservice.util.ObjectConverter;

@Component
public class ProductServiceImpl implements ProductService {
	private ProductRepository repository;
	
	public ProductServiceImpl(ProductRepository repository) {
		this.repository= repository;
	}
	

	@Override
	public ProductDTO addProduct(Product product) {
		Product savedProduct= repository.save(product);
		ProductDTO productDTO= null;
		
		if(savedProduct!= null){
			productDTO= new ProductDTO();
			ObjectConverter.convertProductToProductDTO(savedProduct, productDTO);
		}
		return productDTO;
	}
	
	@Override
	public List<ProductDTO> getAllProducts() {
		List<Product> productList= repository.findAll();
		List<ProductDTO> productDtos= null;
		
		if(productList!= null && productList.size()>0){
			productDtos= new ArrayList<ProductDTO>();
			
			for(Product product: productList){
				ProductDTO productDTO= new ProductDTO();
				ObjectConverter.convertProductToProductDTO(product, productDTO);
				productDtos.add(productDTO);
			}
		}
		
		return productDtos;
	}
	
	@Override
	public ProductDTO getProductById(Long productId) {
		Product fetchedProduct= repository.getOne(productId);
		ProductDTO productDTO= null;
		if(fetchedProduct!= null){
			productDTO= new ProductDTO();
			ObjectConverter.convertProductToProductDTO(fetchedProduct, productDTO);
		}
		return productDTO;
	}
	
	@Override
	public void updateProduct(Product product, Long productId) {
		Product fetchedProduct= repository.getOne(productId);
		if(fetchedProduct!= null){
			fetchedProduct.setName(product.getName());
			fetchedProduct.setDescription(product.getDescription());
			fetchedProduct.setPrice(product.getPrice());
			repository.save(fetchedProduct);
		}
	}


	@Override
	public void deleteProduct(Long productId) {
		repository.delete(repository.getOne(productId));
	}
}
