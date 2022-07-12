package com.shiv.blog.main.services.impl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiv.blog.main.exceptions.ResourceNotFoundException;
import com.shiv.blog.main.model.Category;
import com.shiv.blog.main.model.Post;
import com.shiv.blog.main.model.User;
import com.shiv.blog.main.payload.PostDto;
import com.shiv.blog.main.repositories.CategoryRepo;
import com.shiv.blog.main.repositories.PostRepo;
import com.shiv.blog.main.repositories.UserRepos;
import com.shiv.blog.main.services.IPostService;

@Service
public class PostServiceImpl implements IPostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepos userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userid", userId));
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category Id", categoryId));
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("ab.png");
		post.setAddedDate(new Date());
		post.setCategory(category);
		post.setUser(user);
		Post createPost = this.postRepo.save(post);
		return this.modelMapper.map(createPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Post> getAllPost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post getSinglePostById(Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getPostByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getPostByUser(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> serachPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
