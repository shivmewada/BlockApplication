package com.shiv.blog.main.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

	private Integer categoryId;
	
	@NotEmpty
	@Size(min = 2,message = "Category title must be grater then 2 charactor...!")
	private String categoryTitle;
	
	@NotEmpty
	@Size(min = 2,message = "Category description must be 2 charactor...!")
	private String categoryDescription;
}
