package com.puneet.service;

import org.springframework.stereotype.Service;

import com.puneet.exception.ProductException;
import com.puneet.model.Cart;
import com.puneet.model.CartItem;
import com.puneet.model.Product;
import com.puneet.model.User;
import com.puneet.repository.CartRepository;
import com.puneet.request.AddItemRequest;

@Service
public class CartServiceImpl implements CartService {

	private CartRepository cartRepository;
	private CartItemService cartItemService;
	private ProductService productService;

	public CartServiceImpl(CartRepository cartRepository, CartItemService cartItemService,
			ProductService productService) {
		this.cartItemService = cartItemService;
		this.cartRepository = cartRepository;
		this.productService = productService;
	}

	@Override
	public Cart createCart(User user) {
		Cart cart = new Cart();
		cart.setUser(user);

		return cartRepository.save(cart);

	}

	@Override
	public String addCartItem(Long userId, AddItemRequest req) throws ProductException {
		Cart cart = cartRepository.findByUserId(userId);
		Product product = productService.findProductById(req.getProductId());
		CartItem isPresent = cartItemService.isCartItemExist(cart, product, req.getSize(), userId);
		if (isPresent == null) {
			CartItem cartItem = new CartItem();
			cartItem.setProduct(product);
			cartItem.setCart(cart);
			cartItem.setQuantity(req.getQuantity());
			cartItem.setUserId(userId);

			int price = req.getQuantity() * product.getDiscountedPrice();
			cartItem.setPrice(price);
			cartItem.setSize(req.getSize());

			CartItem createdCartItem = cartItemService.createCartItem(cartItem);
			cart.getCartItems().add(createdCartItem);

		}
		return "Item Added To Cart";
	}

	@Override
	public Cart findUserCart(Long userId) {
		Cart cart = cartRepository.findByUserId(userId);
		int totalPrice = 0;
		int totalDiscountedPrice = 0;
		int totalItem = 0;
		for (CartItem cartsItem : cart.getCartItems()) {
			totalPrice += cartsItem.getPrice();
			totalDiscountedPrice += cartsItem.getDiscountedPrice();
			totalItem += cartsItem.getQuantity();
		}

		cart.setTotalPrice(totalPrice);
		cart.setTotalItem(cart.getCartItems().size());
		cart.setTotalDiscountedPrice(totalDiscountedPrice);
		cart.setDiscount(totalPrice - totalDiscountedPrice);
		cart.setTotalItem(totalItem);

		return cartRepository.save(cart);

	}

}
