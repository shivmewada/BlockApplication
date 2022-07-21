package com.shiv.blog.main.services;

import com.shiv.blog.main.payload.CommentsDto;

public interface ICommentService {
	
	CommentsDto createComment(CommentsDto commentDto, Integer postId,Integer userId);

	void deleteComment(Integer commentId);
}
