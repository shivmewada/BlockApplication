package com.shiv.blog.main.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shiv.blog.main.model.Category;
import com.shiv.blog.main.model.Post;
import com.shiv.blog.main.model.User;

public interface PostRepo extends JpaRepository<Post, Integer>{

	Page<Post> findByUser(User user,Pageable p); 
	Page<Post> findByCategory(Category category,Pageable p); 
	List<Post> findByTitleContaining(String title);
	
}
