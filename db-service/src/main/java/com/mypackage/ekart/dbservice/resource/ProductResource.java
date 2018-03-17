package com.mypackage.ekart.dbservice.resource;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.mypackage.ekart.dbservice.dto.ProductDTO;
import com.mypackage.ekart.dbservice.model.Product;
import com.mypackage.ekart.dbservice.service.ProductService;

@RestController
@RequestMapping(value="/rest/db/products")
public class ProductResource {
	private ProductService productService;
	
	public ProductResource(ProductService productService){
		this.productService= productService;
	}
	
	@PostMapping
	public ResponseEntity<ProductDTO> addProduct(@RequestBody Product product, UriComponentsBuilder builder){
		ProductDTO productDTO= productService.addProduct(product);
		UriComponents components= builder.path("/rest/db/products/{id}").buildAndExpand(productDTO.getProductId());
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(components.toUri());
		
		return new ResponseEntity<ProductDTO>(productDTO, responseHeaders,HttpStatus.CREATED);
	}
	
	@GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDTO> getAllProducts(){
		return productService.getAllProducts();
	}
	
	@RequestMapping(value="/{productId}", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public ProductDTO getProductById(@PathVariable Long productId){
		return productService.getProductById(productId);
	}
	
	@RequestMapping(value= "/{productId}", method= RequestMethod.PUT)
	public ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable Long productId, UriComponentsBuilder  builder){
		productService.updateProduct(product, productId);
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value= "/{productId}", method= RequestMethod.DELETE)
	public ResponseEntity<?> deleteProduct(@PathVariable Long productId, UriComponentsBuilder  builder){
		productService.deleteProduct(productId);
		return ResponseEntity.noContent().build();
		
	}

}
