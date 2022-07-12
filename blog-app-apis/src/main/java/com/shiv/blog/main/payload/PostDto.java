package com.shiv.blog.main.payload;

import java.util.Date;

import com.shiv.blog.main.model.Category;
import com.shiv.blog.main.model.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private String title;
	private String contain;
	private String imageName;
	private Date addedDate;
	private User user;
	private Category category;
}
