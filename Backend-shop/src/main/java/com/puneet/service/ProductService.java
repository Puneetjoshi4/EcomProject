package com.puneet.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.puneet.exception.ProductException;
import com.puneet.model.Product;
import com.puneet.request.CreateProductRequest;

public interface ProductService {

	public Product createProduct(CreateProductRequest req);

	public String deleteProduct(Long productId) throws ProductException;

	public List<Product> getAllProducts();

	public Product updateProduct(Long productId, Product req) throws ProductException;

	public Product findProductById(Long Id) throws ProductException;

	public List<Product> findProductByCategory(String category);

	public Page<Product> getAllProduct(String category, List<String> color, List<String> size, Integer minPrice,
			Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize);

	public List<Product> searchProduct(String query);

}
