package com.shiv.blog.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shiv.blog.main.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
