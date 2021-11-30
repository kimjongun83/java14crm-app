package com.myclass.service;

import org.mindrot.jbcrypt.BCrypt;

import com.myclass.dto.UserDto;
import com.myclass.repository.UserRepository;

public class AuthService {

	private UserRepository userRepository;
	
	public AuthService() {
		userRepository = new UserRepository();
	}
	
	public UserDto login(String email, String passWord) {
		UserDto userDto = userRepository.getUserDtoByEmail(email);
		if(userDto == null) {
			return null;
		}
		if(! BCrypt.checkpw(passWord, userDto.getPassWord())) {
			return null;
		}
		return userDto;
	}
}
