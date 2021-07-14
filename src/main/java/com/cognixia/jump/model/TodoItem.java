package com.cognixia.jump.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

	public TodoItem() {
		this(-1L, null, false, null, null);
	}
	public TodoItem(Long id, String description, boolean completed, Date dueDate, Date dateOfCreation) {
		super();
		this.id = id;
		this.description = description;
		this.completed = completed;
		this.dueDate = dueDate;
		this.dateOfCreation = dateOfCreation;
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
	
}
