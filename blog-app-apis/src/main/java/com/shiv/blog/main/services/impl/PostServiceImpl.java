package com.shiv.blog.main.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
		post.setUser(user);
		post.setCategory(category);
		Post createPost = this.postRepo.save(post);
		return this.modelMapper.map(createPost,PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post existPost =this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post Id :", postId));
		existPost.setTitle(postDto.getTitle());
		existPost.setContain(postDto.getContain());
		existPost.setImageName(postDto.getImageName());
		Post updatePost=this.postRepo.save(existPost);
		return this.modelMapper.map(updatePost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post =this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post Id :", postId));
	    this.postRepo.delete(post);
	}

	@Override
	public List<PostDto> getAllPost() {
		List<Post> listAll=this.postRepo.findAll();
		List<PostDto> listPostDto=listAll.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return listPostDto;
	}

	@Override
	public PostDto getSinglePostById(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("POst", "Post Id :", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
 
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id", categoryId));
		List<Post> posts=this.postRepo.findByCategory(cat);
		List<PostDto> listPostDto=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return listPostDto;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User users=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User Id", userId));
		List<Post> posts=this.postRepo.findByUser(users);
		List<PostDto> listPostDto=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return listPostDto;
	}

	@Override
	public List<Post> serachPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
