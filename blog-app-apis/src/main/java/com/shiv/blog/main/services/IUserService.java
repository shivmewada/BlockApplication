package com.shiv.blog.main.services;

import java.util.List;

import com.shiv.blog.main.payload.UserDto;

public interface IUserService {

	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user,Integer userId);
	
	UserDto getUserById(Integer userId);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer usrId);
}
