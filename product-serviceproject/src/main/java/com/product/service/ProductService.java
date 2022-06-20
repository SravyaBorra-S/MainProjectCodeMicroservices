package com.product.service;

import java.util.List;

import com.product.entity.Product;
//import com.userservice.entity.User;



public interface ProductService {

	    Product getProductById(Long id);
	    List<Product> getAllProducts();
	    List<Product> sortProduct(List<String> sortParams);
	    List<Product> filterByPrice(float priceStart,float priceEnd);
	    List<Product> filterByRating(float _minRating,float _maxRating);
	    Product saveProduct(Product pro);
	    void deleteProduct(Long id);
	    
	   // Product sortProduct (List<String> sortParams);
	   
	   // Product updateProduct(Long id, Product pro);
	    Product updateProduct(Long id, Product product);

}
