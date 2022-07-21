package com.shiv.blog.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shiv.blog.main.payload.ApiResponse;
import com.shiv.blog.main.payload.CommentsDto;
import com.shiv.blog.main.services.ICommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
  
	@Autowired
	private ICommentService commentService;
	
	@PostMapping("/post/{postId}/user/{userId}/create")
	public ResponseEntity<CommentsDto> createComment(@RequestBody CommentsDto commentDto,@PathVariable Integer postId,@PathVariable Integer userId){
		CommentsDto createComment = this.commentService.createComment(commentDto, postId,userId);
	 return new ResponseEntity<CommentsDto>(createComment,HttpStatus.OK);
	}
	
	@GetMapping("/delete/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
     this.commentService.deleteComment(commentId);
	 return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Sucessfully...!",true),HttpStatus.OK);
	}
}
