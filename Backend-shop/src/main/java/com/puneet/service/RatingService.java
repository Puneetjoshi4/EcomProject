package com.puneet.service;

import java.util.List;

import com.puneet.exception.ProductException;
import com.puneet.model.Rating;
import com.puneet.model.User;
import com.puneet.request.RatingRequest;

public interface RatingService {

	public Rating createRating(RatingRequest req, User user) throws ProductException;

	public List<Rating> getProductsRating(Long productId);

}
