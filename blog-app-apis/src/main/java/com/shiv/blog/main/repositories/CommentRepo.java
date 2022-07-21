package com.shiv.blog.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shiv.blog.main.model.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
