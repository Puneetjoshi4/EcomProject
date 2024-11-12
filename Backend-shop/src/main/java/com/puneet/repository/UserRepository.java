package com.puneet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.puneet.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);

}
