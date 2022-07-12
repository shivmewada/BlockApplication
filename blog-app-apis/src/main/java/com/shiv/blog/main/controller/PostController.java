package com.shiv.blog.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shiv.blog.main.payload.PostDto;
import com.shiv.blog.main.services.IPostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private IPostService postService;
	
	@PostMapping("/user/{usrId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer categoryId){
		PostDto newPost=this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(newPost,HttpStatus.CREATED);
	}
}
