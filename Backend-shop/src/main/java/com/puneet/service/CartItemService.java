package com.puneet.service;

import com.puneet.exception.CartItemException;
import com.puneet.exception.UserException;
import com.puneet.model.Cart;
import com.puneet.model.CartItem;
import com.puneet.model.Product;

public interface CartItemService {

	public CartItem createCartItem(CartItem cartItem);

	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException;

	public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);

	public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException;

	public CartItem findCartItemById(Long cartItemId) throws CartItemException;

}
