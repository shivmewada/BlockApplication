package com.shiv.blog.main.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiv.blog.main.exceptions.ResourceNotFoundException;
import com.shiv.blog.main.model.User;
import com.shiv.blog.main.payload.UserDto;
import com.shiv.blog.main.repositories.UserRepos;
import com.shiv.blog.main.services.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepos userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.dtoToUser(userDto);
		User saveUser=this.userRepo.save(user);
		return this.userTodto(saveUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
     User users=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		users.setName(userDto.getName());
		users.setEmail(userDto.getEmail());
		users.setPassword(userDto.getPassword());
		users.setAbout(userDto.getAbout());
		User updateUser=this.userRepo.save(users);
		UserDto updateDto=this.userTodto(updateUser);
     return updateDto;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user =this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		return this.userTodto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users=this.userRepo.findAll();
		List<UserDto> userDtos=users.stream().map(user->this.userTodto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user =this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        this.userRepo.delete(user);

	}
	private User dtoToUser(UserDto userDto) {
		User user =this.modelMapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		return user;
	}
	
	public UserDto userTodto(User user) {
		UserDto userDto =this.modelMapper.map(user, UserDto.class);
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		return userDto;
	}

}
