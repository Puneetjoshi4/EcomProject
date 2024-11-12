package com.puneet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.puneet.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
	

}
 