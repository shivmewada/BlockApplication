package com.shiv.blog.main.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private int id;
	
	@Column(name="user_name" ,nullable = false,length = 100)
	private String name;
	
	@Column(name="user_email" ,nullable = false,length = 20)
	private String email;
	
	@Column(name="user_password" ,nullable = false,length = 20)
	private String password;
	
	@Column(name="user_about" ,nullable = false,length = 1000)
	private String about;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Post> posts =new ArrayList<>();
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private Set<Comment> comments=new HashSet<Comment>();

}
