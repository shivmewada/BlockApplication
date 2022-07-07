package com.shiv.blog.main.services;

import java.util.List;

import com.shiv.blog.main.payload.CategoryDto;

public interface ICategoryService {

	//create
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	//delete
	void deleteCategory(Integer categoryId);
	//get
	
	CategoryDto getCategoryById(Integer categoryId);
	//getAllrecord
	
	List<CategoryDto> getAllCategories();
}
