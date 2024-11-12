package com.puneet.service;

import com.puneet.exception.ProductException;
import com.puneet.model.Cart;
import com.puneet.model.User;
import com.puneet.request.AddItemRequest;

public interface CartService {

	public Cart createCart(User user);

	public String addCartItem(Long userId, AddItemRequest req) throws ProductException;

	public Cart findUserCart(Long userId);

}
