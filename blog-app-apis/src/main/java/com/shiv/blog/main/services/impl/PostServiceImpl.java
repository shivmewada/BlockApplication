package com.shiv.blog.main.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.shiv.blog.main.exceptions.ResourceNotFoundException;
import com.shiv.blog.main.model.Category;
import com.shiv.blog.main.model.Post;
import com.shiv.blog.main.model.User;
import com.shiv.blog.main.payload.ApiResponse;
import com.shiv.blog.main.payload.PostDto;
import com.shiv.blog.main.payload.PostResponce;
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
	public PostResponce getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		Sort sort=(sortDir.equalsIgnoreCase("asc"))?sort=Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
//		if(sortDir.equalsIgnoreCase("asc")) {
//			sort=Sort.by(sortBy).ascending();
//		}else {
//			sort=Sort.by(sortBy).descending();	
//		}
		Pageable p= PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> listAll=this.postRepo.findAll(p);
		 List<Post> allPost=listAll.getContent();
		List<PostDto> listPostDto=allPost.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponce postRes=new PostResponce();
		postRes.setContain(listPostDto);
		postRes.setPageNumber(listAll.getNumber());
		postRes.setPageSize(listAll.getSize());
		postRes.setTotalElements(listAll.getTotalElements());
		postRes.setTotalPages(listAll.getTotalPages());
		postRes.setLastPage(listAll.isLast());
		return postRes;
	}

	@Override
	public PostDto getSinglePostById(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("POst", "Post Id :", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostResponce getPostByCategory(Integer categoryId, Integer pageNumber, Integer pageSize) {

		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
		Pageable p = PageRequest.of(pageNumber, pageSize);
		Page<Post> posts = this.postRepo.findByCategory(cat, p);
		List<Post> allPosts = posts.getContent();
		List<PostDto> listPostDto = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		PostResponce postResponse = new PostResponce();
		postResponse.setContain(listPostDto);
		postResponse.setPageNumber(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalPages(posts.getTotalPages());
		postResponse.setTotalElements(posts.getTotalElements());
		postResponse.setLastPage(posts.isLast());
		return postResponse;
	}

	@Override
	public PostResponce getPostByUser(Integer userId,Integer pageNumber,Integer pageSize) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","user id",userId));
        Pageable p=PageRequest.of(pageNumber,pageSize);
        Page<Post> postPage=this.postRepo.findByUser(user,p);
        List<Post> allPosts=postPage.getContent();
        List<PostDto> postDtos=allPosts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        PostResponce postResponse=new PostResponce();
        postResponse.setContain(postDtos);
        postResponse.setPageNumber(postPage.getNumber());
        postResponse.setPageSize(postPage.getSize());
        postResponse.setTotalPages(postPage.getTotalPages());
        postResponse.setTotalElements(postPage.getTotalElements());
        postResponse.setLastPage(postPage.isLast());
        return postResponse;
	}

	@Override
	public List<PostDto> serachPosts(String keyword) {
		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		List<PostDto> listDto = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return listDto;
	}

}
