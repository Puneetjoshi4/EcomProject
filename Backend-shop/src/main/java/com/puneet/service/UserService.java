package com.puneet.service;

import com.puneet.model.User;
import com.puneet.exception.UserException;

public interface UserService {

	public User findUserById(long userId) throws UserException;

	public User findUserProfileByJwt(String jwt) throws UserException;

}
