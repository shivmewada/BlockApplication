package com.shiv.blog.main.services;

import java.util.List;

import com.shiv.blog.main.model.Post;
import com.shiv.blog.main.payload.PostDto;
import com.shiv.blog.main.payload.PostResponce;

public interface IPostService {

	//create
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	//update post
	PostDto updatePost(PostDto postDto ,Integer postId);
	
	//delete post
	
	void deletePost(Integer postId);
	
	//Get all post
	
	PostResponce getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	//Single post
	
	PostDto getSinglePostById(Integer postId);
	
	// get category by Post
	
	PostResponce getPostByCategory(Integer categoryId,Integer postNumber,Integer postSize); 
	
	//get user by post
	
	PostResponce getPostByUser(Integer userId,Integer postNumber,Integer postSize);

	//search post
	
	List<PostDto> serachPosts(String keyword);
}
