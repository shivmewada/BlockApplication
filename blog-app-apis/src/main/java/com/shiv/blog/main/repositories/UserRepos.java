package com.shiv.blog.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shiv.blog.main.model.User;

public interface UserRepos extends JpaRepository<User, Integer>{

}
