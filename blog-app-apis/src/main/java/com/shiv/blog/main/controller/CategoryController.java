package com.shiv.blog.main.controller;



import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shiv.blog.main.payload.ApiResponse;
import com.shiv.blog.main.payload.CategoryDto;
import com.shiv.blog.main.services.ICategoryService;

@RestController
@RequestMapping("/apis/categories")
public class CategoryController {

	@Autowired
	private ICategoryService categoryService;
	
	//create category
	@PostMapping("/create")
	public ResponseEntity<CategoryDto> createcategory(@Valid @RequestBody CategoryDto catDto) {
		CategoryDto createCategoryDto=this.categoryService.createCategory(catDto);
		return new ResponseEntity<CategoryDto>(createCategoryDto,HttpStatus.CREATED);
	}
	
	//update category
	@PostMapping("/update/{categoryId}")
	public ResponseEntity<CategoryDto> updatecategory(@Valid @RequestBody CategoryDto catDto,@PathVariable Integer categoryId) {
		CategoryDto updateCategoryDto=this.categoryService.updateCategory(catDto, categoryId);
		return new ResponseEntity<CategoryDto>(updateCategoryDto,HttpStatus.OK);
	}
	
	//delete category
	
	@GetMapping("/delete/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity(Map.of("message","category deleted sucessfuly"),HttpStatus.OK);
	}
	
	// get a category
	@GetMapping("/getSingalCategory/{categoryId}")
	public ResponseEntity<CategoryDto> getSingalCategory(@PathVariable Integer categoryId){
		return ResponseEntity.ok(this.categoryService.getCategoryById(categoryId));
	}
	
	// get all category
	@GetMapping("/getAllCategory")
	public ResponseEntity<List<CategoryDto>> getAllUsers(){
		return ResponseEntity.ok(this.categoryService.getAllCategories());
	}
}
