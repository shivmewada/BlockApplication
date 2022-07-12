package com.shiv.blog.main.services;

import java.util.List;

import com.shiv.blog.main.model.Post;
import com.shiv.blog.main.payload.PostDto;

public interface IPostService {

	//create
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	//update post
	PostDto updatePost(PostDto postDto ,Integer postId);
	
	//delete post
	
	void deletePost(Integer postId);
	
	//Get all post
	
	List<PostDto> getAllPost();
	
	//Single post
	
	PostDto getSinglePostById(Integer postId);
	
	// get category by Post
	
	List<PostDto> getPostByCategory(Integer categoryId); 
	
	//get user by post
	
	List<PostDto> getPostByUser(Integer userId);

	//search post
	
	List<Post> serachPosts(String keyword);
}
