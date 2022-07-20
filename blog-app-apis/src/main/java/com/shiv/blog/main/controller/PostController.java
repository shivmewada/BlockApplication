package com.shiv.blog.main.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shiv.blog.main.config.AppConstaint;
import com.shiv.blog.main.model.Post;
import com.shiv.blog.main.payload.ApiResponse;
import com.shiv.blog.main.payload.PostDto;
import com.shiv.blog.main.payload.PostResponce;
import com.shiv.blog.main.services.IFileService;
import com.shiv.blog.main.services.IPostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private IPostService postService;
	
	@Autowired
	private IFileService fileService;
	
	@Value("${project.image}")
	private String path;
	
//	private String path="/home/shiv/Pictures/";
	
	
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
	public ResponseEntity<PostResponce> getPostByUser(
			@PathVariable Integer userId,
            @RequestParam(value = "pageNumber",defaultValue = AppConstaint.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = AppConstaint.PAGE_SIZE,required = false) Integer pageSize
			){
		PostResponce posts= this.postService.getPostByUser(userId,pageNumber,pageSize);
		return new ResponseEntity<PostResponce>(posts,HttpStatus.OK);
	}
	
	// get by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<PostResponce> getPostByCategory(
			@PathVariable Integer categoryId,
			@RequestParam(value = "pageNumber",defaultValue = AppConstaint.PAGE_NUMBER,required = false)Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = AppConstaint.PAGE_SIZE,required = false)Integer pageSize
			){
		PostResponce allPost= this.postService.getPostByCategory(categoryId,pageNumber,pageSize);
		return new ResponseEntity<PostResponce>(allPost,HttpStatus.OK);
	}
	// get all post
	
	@GetMapping("/getAllPost")
	public ResponseEntity<PostResponce> getAllPost(
			@RequestParam(value = "pageNumber",defaultValue = AppConstaint.PAGE_NUMBER,required = false)Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = AppConstaint.PAGE_SIZE,required = false)Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstaint.SORT_BY,required = false)String sortBy,
			@RequestParam(value = "sortDir",defaultValue = AppConstaint.SORT_DIR,required = false)String sortDir
			){
		PostResponce allPost = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponce>(allPost,HttpStatus.OK);
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
	
	//search 
	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<PostDto>> serachByTitle(
			@PathVariable("keyword") String keyword
			){
		List<PostDto> results = this.postService.serachPosts(keyword);
		return new ResponseEntity<List<PostDto>>(results,HttpStatus.OK);
	}
	
	//upload image
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadImage(@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId
			) throws IOException{
		PostDto postDto = this.postService.getSinglePostById(postId);
		String fileName = this.fileService.uploadImage(path, image);
		postDto.setImageName(fileName);
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	//download image
	@GetMapping(value = "/post/image/{imageName}",produces =MediaType.IMAGE_JPEG_VALUE)
	public void downLoadImage(
			@PathVariable("imageName") String imageName,
			HttpServletResponse responce
			)throws IOException {
	       InputStream resources = this.fileService.getResources(path, imageName);
	       resources
	       
		
	}
}
