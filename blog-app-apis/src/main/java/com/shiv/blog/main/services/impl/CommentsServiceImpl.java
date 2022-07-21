package com.shiv.blog.main.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiv.blog.main.exceptions.ResourceNotFoundException;
import com.shiv.blog.main.model.Comment;
import com.shiv.blog.main.model.Post;
import com.shiv.blog.main.model.User;
import com.shiv.blog.main.payload.CommentsDto;
import com.shiv.blog.main.repositories.CommentRepo;
import com.shiv.blog.main.repositories.PostRepo;
import com.shiv.blog.main.repositories.UserRepos;
import com.shiv.blog.main.services.ICommentService;

@Service
public class CommentsServiceImpl implements ICommentService{

	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private PostRepo postRep;
	
	@Autowired
	private UserRepos userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentsDto createComment(CommentsDto commentDto, Integer postId,Integer userId) {
		Post post = this.postRep.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","PostId",postId));
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","user Id",userId) );
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		comment.setUser(user);
		Comment createComment = this.commentRepo.save(comment);
		return this.modelMapper.map(createComment, CommentsDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "cooment Id", commentId));
	    this.commentRepo.delete(comment);
	}

}
