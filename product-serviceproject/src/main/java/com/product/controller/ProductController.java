package com.product.controller;


import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.entity.Product;
import com.product.header.HeaderGenerator;
import com.product.service.ProductService;




@RestController
@RequestMapping("/api/product")

public class ProductController {

    @Autowired
    private HeaderGenerator headerGenerator;

    @Autowired
    private ProductService productService;

   
    @PostMapping (value = "/products")
    public ResponseEntity<Product> addProduct(@RequestBody Product prodct, HttpServletRequest request){
    	System.out.println("entered");
    	if(prodct != null)
    		try {
    			productService.saveProduct(prodct);
    			return new ResponseEntity<Product>(
    					prodct,
    					headerGenerator.getHeadersForSuccessPostMethod(request, prodct.getId()),
    					HttpStatus.CREATED);
    		}catch (Exception e) {
    			e.printStackTrace();
    			return new ResponseEntity<Product>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping (value = "/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> prodcts =  productService.getAllProducts();
        if(!prodcts.isEmpty()) {
        	return new ResponseEntity<List<Product>>(
        		prodcts,
        		headerGenerator.getHeadersForSuccessGetMethod(),
        		HttpStatus.OK);
        }
        return new ResponseEntity<List<Product>>(
        		headerGenerator.getHeadersForError(),
        		HttpStatus.NOT_FOUND);
    }


    
    @GetMapping (value = "/{productId}")
    public ResponseEntity<Product> getUserById(@PathVariable("productId") Long id){
    	System.out.println("entered idsearch"+ id);
    	Product product = productService.getProductById(id);
        if(product != null) {
    		return new ResponseEntity<Product>(
    				product,
    				headerGenerator.
    				getHeadersForSuccessGetMethod(),
    				HttpStatus.OK);
    	}
        return new ResponseEntity<Product>(
        		headerGenerator.getHeadersForError(),
        		HttpStatus.NOT_FOUND);
    }
    
    
    @GetMapping(value="/sortBy", params = "sort")
    public List<Product> sortProduct(@RequestParam String sort){
    	System.out.println("entered idsearch"+ sort);
      List<String> sortParams= Arrays.stream(sort.split("_")).toList();
      return productService.sortProduct(sortParams);      

  }
  @GetMapping("/filter/price")
  public List<Product> filterByPrice(@RequestParam String startPrice, @RequestParam String endPrice){
      float priceStart= Float.parseFloat(startPrice);
      float priceEnd=Float.parseFloat(endPrice);
      return productService.filterByPrice(priceStart,priceEnd);

  }
  @GetMapping("/filter/rating")
  public List<Product> filterByRating(@RequestParam String minRating, @RequestParam String maxRating) {
      float _minRating = Float.parseFloat(minRating);
      float _maxRating = Float.parseFloat(maxRating);
      return productService.filterByRating(_minRating, _maxRating);
  }
    
  @DeleteMapping("/{productId}")
  //@ResponseStatus(HttpStatus.OK)
   public String deleteProduct(@PathVariable(value = "productId") Long productId){
    productService.deleteProduct(productId);
    return "Deleted Successfully";
  }
  
      
    @PutMapping("/{productId}")
    // @ResponseStatus(HttpStatus.OK)
    public Product updateProduct(@PathVariable(value = "productId") Long id, @RequestBody Product product) {
    	System.out.println("entered idsearch"+ id);    
    	return productService.updateProduct(id, product);
    }

}
