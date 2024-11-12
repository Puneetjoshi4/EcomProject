package com.puneet.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.puneet.exception.ProductException;
import com.puneet.model.Product;
import com.puneet.model.Rating;
import com.puneet.model.User;
import com.puneet.repository.RatingRepository;
import com.puneet.request.RatingRequest;

@Service
public class RatingServiceImpl implements RatingService {

	private RatingRepository ratingRepository;
	private ProductService productService;

	public RatingServiceImpl(RatingRepository ratingRepository, ProductService productService) {
		this.productService = productService;
		this.ratingRepository = ratingRepository;
	}

	@Override
	public Rating createRating(RatingRequest req, User user) throws ProductException {
		Product product = productService.findProductById(req.getProductId());

		Rating rating = new Rating();
		rating.setProduct(product);
		rating.setUser(user);
		rating.setRating(req.getRating());
		rating.setCreatedAt(LocalDateTime.now());

		return ratingRepository.save(rating);

	}

	@Override
	public List<Rating> getProductsRating(Long productId) {
		return ratingRepository.getAllProductsRating(productId);

	}

}
