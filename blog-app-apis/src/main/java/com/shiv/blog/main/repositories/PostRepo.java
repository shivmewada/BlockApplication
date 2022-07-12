package com.shiv.blog.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shiv.blog.main.model.Category;
import com.shiv.blog.main.model.Post;
import com.shiv.blog.main.model.User;

public interface PostRepo extends JpaRepository<Post, Integer>{

	List<Post> findByUser(User user); 
	List<Post> findByCategory(Category category); 
}
