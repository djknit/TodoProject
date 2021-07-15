package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public enum Role { ROLE_USER, ROLE_ADMIN }
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column( unique = true )
	@Size( min = 3, message = "Your username must be at least 3 characters long." )
	@NotBlank( message = "Your username cannot be blank." )
	private String username;
	
	@NotBlank( message = "Your password must not be blank." )
	@Size( min = 4, message = "Your password must be at least 4 characters long." )
	@Column( nullable = false )
	private String password;
	
	@NotBlank( message = "First name cannot be blank." )
	private String firstName;
	
	@NotBlank( message = "Last name cannot be blank." )
	private String lastName;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;
	
	@JsonManagedReference
	@OneToMany( mappedBy = "user", cascade = CascadeType.ALL )
	private List<TodoItem> todoItems;
	
	public User() {
		this(-1L, "N/A", "N/A", Role.ROLE_USER);
	}
	public User(Long id, String username, String password, Role role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	public List<TodoItem> getTodoItems() {
		return todoItems;
	}
	public void setTodoItems(List<TodoItem> todoItems) {
		this.todoItems = todoItems;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password
				+ ", role=" + role + "]";
	}
}