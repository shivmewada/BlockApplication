package com.shiv.blog.main.payload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.shiv.blog.main.model.Comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private Integer postId;
	private String title;
	private String contain;
	private String imageName;
	private Date addedDate;
	private CategoryDto category;
	private UserDto user;
	private Set<CommentsDto> comments=new HashSet<>();
	
}
