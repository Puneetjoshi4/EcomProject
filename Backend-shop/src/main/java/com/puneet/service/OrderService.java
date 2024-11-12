package com.puneet.service;

import java.util.List;

import com.puneet.exception.OrderException;
import com.puneet.model.Address;
import com.puneet.model.Order;
import com.puneet.model.User;

public interface OrderService {

	public Order createOrder(User user, Address shippingAdress);

	public Order findOrderById(Long orderId) throws OrderException;

	public List<Order> usersOrderHistory(Long userId);

	public Order placedOrder(Long orderId) throws OrderException;

	public Order confirmedOrder(Long orderId) throws OrderException;

	public Order shippedOrder(Long orderId) throws OrderException;

	public Order deliveredOrder(Long orderId) throws OrderException;

	public Order cancelOrder(Long orderId) throws OrderException;

	public List<Order> getAllOrders();

	public void deleteOrder(Long orderId) throws OrderException;

}
