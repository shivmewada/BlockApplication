package com.shiv.blog.main.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;
	
	@NotEmpty
	@Size(min = 3, message = "User name must be grater then 2..!")
	private String name;
	
	@Email(message = "Emial address is not valid !! only acept this you@gmail.com formate..!")
	private String email;
	
	@NotEmpty
	@Size(min = 3,max = 10 ,message = "Password must be grater then 2 and maximum 10 charactor..!")
	private String password;
	
	@NotEmpty
	@Size(min = 2,message = "about must be grater then 2 charactor..!")
	private String about;
}
