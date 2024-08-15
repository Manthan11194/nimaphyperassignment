package com.example.assignment.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.assignment.dtos.CategoryDto;
import com.example.assignment.dtos.ProductDto;
import com.example.assignment.dtos.ProductRequestDto;
import com.example.assignment.models.Category;
import com.example.assignment.models.Products;
import com.example.assignment.services.CategoryService;
import com.example.assignment.services.ProductService;

@RestController
public class ProductController {
	
	@GetMapping("/hello")
	public String hello() {
		return "Hi";
	}
	
	
	private ProductService productService;
	ProductController(ProductService productService){
		this.productService = productService;
	}
	
	//Create a product
	@PostMapping("/products")
	public ResponseEntity<String> createProduct(@RequestBody ProductRequestDto product) {
		String responseBody = new String();
        try {
        	
            responseBody = productService.createProduct(product);
            return new ResponseEntity(responseBody, HttpStatus.OK);
        } catch (Exception e) {
        }
        return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }

	//Get all products
	@GetMapping("/products")
	public ResponseEntity<Page<Products>> getAllProducts(
			@RequestParam(defaultValue = "0") int page ,
			@RequestParam(defaultValue = "10") int size) {
		
        try {
            Pageable pageable = PageRequest.of(page,size);

            Page<Products> products = productService.getAllProducts(pageable);
            return new ResponseEntity(products, HttpStatus.OK);
            
        } catch (Exception e) {
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
		
	//Get Product by id.
	@GetMapping("/products/{id}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable Integer id) {
		ProductDto productDto = new ProductDto();
        try {
        	productDto = productService.getProductById(id);
            return new ResponseEntity(productDto, HttpStatus.OK);
            
        } catch (Exception e) {
        }
        return new ResponseEntity<>(productDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
		
	//Update a product.
	@PutMapping("/products/{id}")
	public ResponseEntity<String> updateProduct(@PathVariable Integer id,@RequestBody ProductDto product) {
		String responseBody = new String();
        try {
        	
            responseBody = productService.updateProduct(id,product);
            return new ResponseEntity(responseBody, HttpStatus.OK);
        } catch (Exception e) {
        }
        return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }
		
	//Delete a category
	@DeleteMapping("/products/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
		String responseBody = new String();
        try {
        	
            responseBody = productService.deleteProduct(id);
            return new ResponseEntity(responseBody, HttpStatus.OK);
        } catch (Exception e) {
        }
        return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
