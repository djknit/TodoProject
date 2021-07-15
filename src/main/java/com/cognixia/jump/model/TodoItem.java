package com.cognixia.jump.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class TodoItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;

	@Column( nullable = false )
	private String description;
	private boolean completed;
	
	@Column( nullable = false )
	private Date dueDate;
	
	@Column( nullable = false )
	private Date dateOfCreation;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn( name = "user_id", referencedColumnName = "id" )
	private User user;

	public TodoItem() {
		this(-1L, "N/A", false, null, null, null);
	}
	public TodoItem(Long id, String description, boolean completed, Date dueDate, Date dateOfCreation, User user) {
		super();
		this.id = id;
		this.description = description;
		this.completed = completed;
		this.dueDate = dueDate;
		this.dateOfCreation = dateOfCreation;
		this.user = user;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getDateOfCreation() {
		return dateOfCreation;
	}
	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
