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
    @PutMapping("/todo/update")
    public ResponseEntity<TodoItem> updateTodo(@RequestBody TodoItem todoItem){
        Optional<TodoItem> todoItemX = repo.findById(todoItem.getId());
        if(todoItemX.isPresent()){
            repo.save(todoItem);

            return ResponseEntity.status(200)
                    .body(todoItem);
        }
        else{
            return ResponseEntity.status(400)
                    .body(new TodoItem());
        }
        //update object
    }

    //update description
    @PatchMapping("/todo/update/description")
    public ResponseEntity<TodoItem> updateTodoDescription(@RequestBody TodoItem todoItem){
        long idSpec = todoItem.getId();
        if(repo.existsById(idSpec)){
            TodoItem todoItem1 = repo.findById(todoItem.getId()).get();
            todoItem1.setDescription(todoItem.getDescription());
            repo.save(todoItem1);

            return ResponseEntity.status(200)
                    .body(todoItem1);
        }
        else{
            return ResponseEntity.status(400)
                    .body(new TodoItem());
        }
    }

    @PatchMapping("/todo/update/completed")
    public ResponseEntity<TodoItem> updateTodoComplete(@RequestBody TodoItem todoItem){
        long idSpec = todoItem.getId();
        if(repo.existsById(idSpec)){
            TodoItem todoItem1 = repo.findById(todoItem.getId()).get();
            todoItem1.setCompleted(true);
            repo.save(todoItem1);

            return ResponseEntity.status(200)
                    .body(todoItem1);
        }

        else{
            return ResponseEntity.status(400)
                    .body(new TodoItem());
        }
    }

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