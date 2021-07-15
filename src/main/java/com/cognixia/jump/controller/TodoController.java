package com.cognixia.jump.controller;

import com.cognixia.jump.model.TodoItem;
import com.cognixia.jump.repository.TodoRepository;
import com.cognixia.jump.repository.UserRepository;
import com.cognixia.jump.service.MyUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RequestMapping("/api")
@RestController
public class TodoController {

    @Autowired
    TodoRepository repo;
    
    @Autowired
    UserRepository userRepo;
    

    //find all todo

    @GetMapping("/todo")
    public List<TodoItem> getAllTodo(){
    	return userRepo.getById(MyUserDetailsService.getCurrentUserId()).getTodoItems();
    }

    //add a new todo

    @PostMapping("/todo/create")
    public TodoItem createTodo(@RequestBody TodoItem todoItem){
        
    	long currDateInMilli = ZonedDateTime.now().toInstant().toEpochMilli();
		Date createdDate = new Date(currDateInMilli); // <- today
		Date dueDate = new Date(currDateInMilli + TimeUnit.DAYS.toMillis(7)); // <- 1 week from now
    	
		todoItem.setDateOfCreation(createdDate);
		
		if (todoItem.getDueDate() == null) {
			todoItem.setDueDate(dueDate);
		}
		
		todoItem.setUser(userRepo.getById(MyUserDetailsService.getCurrentUserId()));
		
    	TodoItem created = repo.save(todoItem);

        return created;
    }

    //update a todo
    @PutMapping("/todo/update/")
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