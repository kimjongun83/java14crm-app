package com.myclass.service;

import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.myclass.dto.UserDto;
import com.myclass.entity.User;
import com.myclass.repository.UserRepository;

public class UserService {
	
	private UserRepository userRepository;
	
	public UserService() {
		userRepository = new UserRepository();
	}
	
	public List<UserDto> findAll(){
		List<UserDto> userDtos = new ArrayList<UserDto>();
		List<User> users = userRepository.findAll();
		for (User user : users) {
			UserDto userDto = new UserDto();
			userDto.setId(user.getId());
			userDto.setEmail(user.getEmail());
			userDto.setPassWord(user.getPassWord());
			userDto.setFullName(user.getFullName());
			userDto.setRoleId(user.getRoleId());
			
			userDtos.add(userDto);
		}
		return userDtos;
	}
	
	public List<UserDto> findAllUserDto(){
		return userRepository.findAllUserDto();
	}
	
	public boolean addUser(UserDto userDto) {
		if(checkExits(userDto)) {
			User user = new User();
			convertToUser(user, userDto);
			return userRepository.addUser(user);
		}
		return false;
	}
	public UserDto getUserDtoById(int id) {
		UserDto userDto;
		User user = userRepository.getUserById(id);
		if(user != null) {
			userDto = new UserDto();
			userDto.setId(user.getId());
			userDto.setEmail(user.getEmail());
			userDto.setPassWord(user.getPassWord());
			userDto.setFullName(user.getFullName());
			userDto.setAvatar(user.getAvatar());
			userDto.setRoleId(user.getRoleId());
			return userDto;
		}
		return null;
	}
	public boolean updateUser(int id, UserDto userDto) {
		if(checkExits(userDto)) {
			User user = userRepository.getUserById(id);
			convertToUser(user, userDto);
			return userRepository.updateUser(id, user);
		}
		return false;
	}
	public boolean deleteUser(int id) {
		return userRepository.deleteUser(id);
	}
	
	private void convertToUser(User user, UserDto userDto) {
		user.setEmail(userDto.getEmail());
		String hashed;
		if(user.getPassWord() == null || (! user.getPassWord().equalsIgnoreCase(userDto.getPassWord()))) {
			hashed = BCrypt.hashpw(userDto.getPassWord(), BCrypt.gensalt());
		}else {
			hashed = userDto.getPassWord();

		}
	 
		//System.out.println("ma pass: "+hashed+" "+ hashed.length());
		user.setPassWord(hashed);
		user.setFullName(userDto.getFullName());
		user.setAvatar(userDto.getAvatar());
		user.setRoleId(userDto.getRoleId());
	}
	
	private boolean checkExits(UserDto userDto) {
		
		if(userDto.getEmail().equals("")||userDto.getPassWord().equals("")||userDto.getFullName().equals("")) {
			return false;
		}
		List<User> user = userRepository.findAll();
		for (User userCheck : user) {
			if((userCheck.getEmail().equalsIgnoreCase(userDto.getEmail())) && (userCheck.getId() != userDto.getId())) {
				return false;
			}
		}
		
		return true;
	}
	public UserDto getUserDtoByName(String name) {
		User entity = userRepository.getUserByName(name);
		UserDto userDto;
		if(entity != null) {
			userDto = new UserDto();
			userDto.setId(entity.getId());
			userDto.setEmail(entity.getEmail());
			userDto.setPassWord(entity.getPassWord());
			userDto.setFullName(entity.getFullName());
			userDto.setAvatar(entity.getAvatar());
			userDto.setRoleId(entity.getRoleId());
			
			return userDto;
		}
		return null;
	}

}
