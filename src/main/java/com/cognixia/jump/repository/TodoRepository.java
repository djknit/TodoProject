package com.cognixia.jump.repository;

import com.cognixia.jump.model.TodoItem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoItem, Long> {

}