package com.product.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.product.entity.Product;
import com.product.repository.ProductRepo;
//import com.userservice.entity.User;



@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	private static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    private ProductRepo ProductRepository;
    
    
	@Override
	public List<Product> getAllProducts() {
		System.out.println("Test12343456667");
		return ProductRepository.findAll();
	}
	
	@Override
	public Product saveProduct(Product pro) {
		// TODO Auto-generated method stub
		System.out.println("Test9887665"+pro);
		return ProductRepository.save(pro);
		
	}
	  @Override
	    public Product getProductById(Long id) {
		  System.out.println("Tes11111111----"+id);
	        return ProductRepository.getOne(id);
	    }
	  
	     @Override
	  public Product updateProduct(Long productId, Product product) {
	    	 System.out.println("entered update");
	        Product _product = ProductRepository.getOne(productId);
	        _product.setName(product.getName());
	        _product.setDescription(product.getDescription());
	        _product.setPrice(product.getPrice());
	        return ProductRepository.save(_product);
	   }

	  
	  @Override
	  public List<Product> sortProduct(List<String> sortParams) {

	        String sortColumn= sortParams.get(0);
	        String direction = sortParams.get(1);

	        if(direction.equals("desc")){
	            return ProductRepository.findAll(Sort.by(Sort.Direction.DESC, sortColumn));
	        }
	        else{
	            return ProductRepository.findAll(Sort.by(Sort.Direction.ASC, sortColumn));
	        }
	  }
//	  
//	  @Override
//	  public List<Product> filterByPrice(String priceStart,String priceEnd) {
//		  
//		  
//	  }
//	  
//	  @Override
//	  public List<Product> filterByRating(String _minRating,String _maxRating) {
//		  
//		  
//	  }
	  
	  @Override
	  public List<Product> filterByPrice(float startPrice, float endPrice) {
	        List<Product>  product= ProductRepository.findAll();
	        return product.stream().filter(p->p.getPrice()>startPrice)
	                .filter(p->p.getPrice()<endPrice).collect(Collectors.toList());
	    }
	 
	  @Override
	    public List<Product> filterByRating(float _minRating, float _maxRating) {
	        List<Product>  product= ProductRepository.findAll();
	        return product.stream().filter(p->p.getRating()>_minRating)
	                .filter(p->p.getRating()<_maxRating).collect(Collectors.toList());
	    }
	  
	  @Override
	  public void deleteProduct(Long productId) {
		  System.out.println("deteprod"+productId);
	        Product product = ProductRepository.findById(productId).orElseThrow();
	        ProductRepository.delete(product);
	    }

}
