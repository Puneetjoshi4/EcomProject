package com.puneet.service;

import java.util.List;

import com.puneet.exception.ProductException;
import com.puneet.model.Review;
import com.puneet.model.User;
import com.puneet.request.ReviewRequest;

public interface ReviewService {

	public Review createReview(ReviewRequest req, User user) throws ProductException;

	public List<Review> getAllReview(Long productId);

}
