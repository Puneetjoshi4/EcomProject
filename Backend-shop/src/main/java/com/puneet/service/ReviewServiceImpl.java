package com.puneet.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.puneet.exception.ProductException;
import com.puneet.model.Product;
import com.puneet.model.Review;
import com.puneet.model.User;
import com.puneet.repository.ProductRepository;
import com.puneet.repository.ReviewRepository;
import com.puneet.request.ReviewRequest;

@Service
public class ReviewServiceImpl implements ReviewService {

	private ReviewRepository reviewRepository;
	private ProductService productService;
	private ProductRepository productRepository;

	public ReviewServiceImpl(ReviewRepository reviewRepository, ProductService productService,
			ProductRepository productRepository) {
		this.reviewRepository = reviewRepository;
		this.productService = productService;
		this.productRepository = productRepository;
	}

	@Override
	public Review createReview(ReviewRequest req, User user) throws ProductException {
		Product product = productService.findProductById(req.getProductId());
		Review review = new Review();
		review.setUser(user);
		review.setProduct(product);
		review.setReview(req.getReview());
		review.setCreatedAt(LocalDateTime.now());

		return reviewRepository.save(review);

	}

	@Override
	public List<Review> getAllReview(Long productId) {

		return reviewRepository.getAllProductsReview(productId);
	}

}
