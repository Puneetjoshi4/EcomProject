package com.puneet.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puneet.exception.ProductException;
import com.puneet.exception.UserException;
import com.puneet.model.Rating;
import com.puneet.model.Review;
import com.puneet.model.User;
import com.puneet.request.RatingRequest;
import com.puneet.service.RatingService;
import com.puneet.service.UserService;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

	private UserService userService;
	private RatingService RatingService;

	public RatingController(UserService userService, RatingService RatingService) {
		this.RatingService = RatingService;
		this.userService = userService;
		// TODO Auto-generated constructor stub
	}

	@PostMapping("/create")
	public ResponseEntity<Rating> createRatingHandler(@RequestBody RatingRequest req,
			@RequestHeader("Authorization") String jwt) throws UserException, ProductException {
		User user = userService.findUserProfileByJwt(jwt);
		Rating rating = RatingService.createRating(req, user);
		return new ResponseEntity<>(rating, HttpStatus.ACCEPTED);
	}

	@GetMapping("/product/{productId}")
	public ResponseEntity<List<Rating>> getProductsReviewHandler(@PathVariable Long productId) {

		List<Rating> ratings = RatingService.getProductsRating(productId);
		return new ResponseEntity<>(ratings, HttpStatus.OK);
	}
}
