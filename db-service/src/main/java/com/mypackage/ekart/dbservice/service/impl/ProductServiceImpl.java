package com.mypackage.ekart.dbservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.cache.annotation.CacheResult;
import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Component;

import com.mypackage.ekart.dbservice.dto.ProductDTO;
import com.mypackage.ekart.dbservice.exception.ProductNotFoundException;
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
		ProductDTO productDTO= null;
		try{
		Product savedProduct= repository.save(product);
		
		
		if(savedProduct!= null){
			productDTO= new ProductDTO();
			ObjectConverter.convertProductToProductDTO(savedProduct, productDTO);
		}
		}catch(RuntimeException e){
			throw new RuntimeException("Something went wrong. Please try again later.");
		}
		return productDTO;
	}
	
	@Override
	@CacheResult
	public List<ProductDTO> getAllProducts() {
		List<ProductDTO> productDtos= null;
		try{
		List<Product> productList= repository.findAll();
		
		
		if(productList!= null && productList.size()>0){
			productDtos= new ArrayList<ProductDTO>();
			
			for(Product product: productList){
				ProductDTO productDTO= new ProductDTO();
				ObjectConverter.convertProductToProductDTO(product, productDTO);
				productDtos.add(productDTO);
			}
		}
		}catch(RuntimeException e){
			throw new RuntimeException("Something went wrong. Please try again later.");
		}
		
		return productDtos;
	}
	
	@Override
	@CacheResult
	public ProductDTO getProductById(Long productId) {
		ProductDTO productDTO= null;
		try{
		Product fetchedProduct= repository.getOne(productId);
		
		if(fetchedProduct!= null){
			productDTO= new ProductDTO();
			ObjectConverter.convertProductToProductDTO(fetchedProduct, productDTO);
		}
		}catch(EntityNotFoundException e){
			throw new ProductNotFoundException("Product with product id: "+productId+ 
					" does not exist. Please try again with correct product id.");
		}catch(RuntimeException e){
			throw new RuntimeException("Something went wrong. Please try again later.");
		}
		return productDTO;
	}
	
	@Override
	public void updateProduct(Product product, Long productId) {
		try{
		Product fetchedProduct= repository.getOne(productId);
		if(fetchedProduct!= null){
			fetchedProduct.setName(product.getName());
			fetchedProduct.setDescription(product.getDescription());
			fetchedProduct.setPrice(product.getPrice());
			repository.save(fetchedProduct);
		}
		}catch(EntityNotFoundException e){
			throw new ProductNotFoundException("Product with product id: "+productId+ 
					" does not exist. Please try again with correct product id.");
		}catch(RuntimeException e){
			throw new RuntimeException("Something went wrong. Please try again later.");
		}
	}


	@Override
	public void deleteProduct(Long productId) {
		try{
			Product product= repository.getOne(productId);
			product.getName();                                                  //Invoking to catch EntityNotFoundException.
			repository.delete(product);
			}catch(EntityNotFoundException e){
				throw new ProductNotFoundException("Product with product id: "+productId+ 
						" does not exist. Please try again with correct product id.");
			}catch(RuntimeException e){
				throw new RuntimeException("Something went wrong. Please try again later.");
			}
	}
}
