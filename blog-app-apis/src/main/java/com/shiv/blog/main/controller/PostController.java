package com.shiv.blog.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shiv.blog.main.model.Post;
import com.shiv.blog.main.payload.ApiResponse;
import com.shiv.blog.main.payload.PostDto;
import com.shiv.blog.main.services.IPostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private IPostService postService;
	
	//create 
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId){
		PostDto newPost=this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(newPost,HttpStatus.CREATED);
	}
	
	// get by user
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
		List<PostDto> listPost= this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(listPost,HttpStatus.OK);
	}
	
	// get by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
		List<PostDto> listPost= this.postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(listPost,HttpStatus.OK);
	}
	// get all post
	
	@GetMapping("/getAllPost")
	public ResponseEntity<List<PostDto>> getAllPost(){
		List<PostDto> listDto=this.postService.getAllPost();
		return new ResponseEntity<List<PostDto>>(listDto,HttpStatus.OK);
	}
	
	// get post by Id
	@GetMapping("/getPostById/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
	  PostDto listDto=this.postService.getSinglePostById(postId);
		return new ResponseEntity<PostDto>(listDto,HttpStatus.OK);
	}
	
	//delete post
	@GetMapping("/delete/{postId}")
	public ApiResponse delete(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ApiResponse("Post deleted successfully....!",true);
	}
	// update Post
	@PostMapping("/update/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
		PostDto updateDto=this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updateDto,HttpStatus.OK);
	}
}
