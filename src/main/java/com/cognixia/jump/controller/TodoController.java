package com.cognixia.jump.controller;

import com.cognixia.jump.model.TodoItem;
import com.cognixia.jump.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class TodoController {

    @Autowired
    TodoRepository repo;

    @GetMapping("/todo")
    public List<TodoItem> getAllTodo(){
        return repo.findAll();
    }

    @PostMapping("/todo")
    public TodoItem createTodo(@RequestBody TodoItem todoItem){
        TodoItem created = repo.save(todoItem);

        return created;
    }

}
