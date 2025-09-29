package com.example.todospring.controllers;

import com.example.todospring.models.Todo;
import com.example.todospring.services.ToDoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class ToDoController {

    private final  ToDoService toDoService;

    public ToDoController(ToDoService toDoService){
        this.toDoService = toDoService;
    }

    //get all
    @GetMapping
    public List<Todo> getAllTodos(){
        return toDoService.getAllTodos();
    }

    @PostMapping
    public Todo createTodo(@RequestParam String title){
        return toDoService.createTodo(title);
    }    @PutMapping("/{id}/status")
    public ResponseEntity<Object> updateTodoStatus(@PathVariable long id) {
        return toDoService.updateTodoStatus(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTodo(@PathVariable long id, @RequestParam String updateTitle){ return toDoService.updateTodo(id,updateTitle);}
}
