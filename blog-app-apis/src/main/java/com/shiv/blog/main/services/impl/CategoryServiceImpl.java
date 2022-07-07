package com.shiv.blog.main.services.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiv.blog.main.exceptions.ResourceNotFoundException;
import com.shiv.blog.main.model.Category;
import com.shiv.blog.main.payload.CategoryDto;
import com.shiv.blog.main.repositories.CategoryRepo;
import com.shiv.blog.main.services.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat=this.modelMapper.map(categoryDto, Category.class);
		Category addCat=this.categoryRepo.save(cat);
		return this.modelMapper.map(addCat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Id", categoryId));
		//cat.setCategoryId(categoryDto.getCategoryId());
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updateCat=this.categoryRepo.save(cat);
		CategoryDto updateDto=this.modelMapper.map(updateCat, CategoryDto.class);
		return updateDto;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Id", categoryId));
        this.categoryRepo.delete(cat);
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Id", categoryId));
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> categoryList=this.categoryRepo.findAll();
		List<CategoryDto> categoryDto=categoryList.stream().map((cat)->this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return categoryDto;
	}

}
