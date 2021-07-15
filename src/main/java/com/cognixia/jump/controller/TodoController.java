package com.cognixia.jump.controller;

import com.cognixia.jump.model.TodoItem;
import com.cognixia.jump.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
public class TodoController {

    @Autowired
    TodoRepository repo;

    //find all todo

    @GetMapping("/todo")
    public List<TodoItem> getAllTodo(){
        return repo.findAll();
    }

    //add a new todo

    @PostMapping("/todo/create")
    public TodoItem createTodo(@RequestBody TodoItem todoItem){
        TodoItem created = repo.save(todoItem);

        return created;
    }

    //update a todo
//    @PutMapping("/todo/update/{todo_id}")
//    public ResponseEntity<TodoItem> updateTodo(@PathVariable long todo_id){
//        Optional<TodoItem> todoItem = repo.findById(todo_id);
//    }

    //delete a todo

    @DeleteMapping("/todo/delete/{todo_id}")
    public ResponseEntity<TodoItem> deleteTodo(@PathVariable long todo_id){
        Optional<TodoItem>  todoItem = repo.findById(todo_id);

        if(todoItem.isPresent()){
            repo.deleteById(todo_id);

            return ResponseEntity.status(200)
                    .body(todoItem.get());
        }
        else{
            return ResponseEntity.status(400)
                                .body(new TodoItem());
        }
    }

}