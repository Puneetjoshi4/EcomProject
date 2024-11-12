package com.puneet.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.puneet.config.JwtProvider;
import com.puneet.exception.UserException;
import com.puneet.model.User;
import com.puneet.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private JwtProvider jwtProvider;

	public UserServiceImpl(UserRepository userRepository, JwtProvider jwtProvider) {
		this.jwtProvider = jwtProvider;
		this.userRepository = userRepository;
	}

	@Override
	public User findUserById(long userId) throws UserException {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			return user.get();
		}
		throw new UserException("User not found id :" + userId);
	}

	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {
		String email = jwtProvider.getEmailFromToken(jwt);
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UserException("user not found with email" + email);

		}
		return user;
	}

}
