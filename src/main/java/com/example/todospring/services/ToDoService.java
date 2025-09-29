package com.example.todospring.services;

import com.example.todospring.models.Todo;
import com.example.todospring.repositories.ToDoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ToDoService {

    private final ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository toDoRepository){
        this.toDoRepository = toDoRepository;
    }

    public List<Todo> getAllTodos(){
        return toDoRepository.findAll();
    }

    public Todo createTodo(String title){
        Todo todo = new Todo();
        todo.setName(title);
        todo.setIsCompleted(false);
        toDoRepository.save(todo);
        return todo;
    }

    public ResponseEntity<Object> updateTodo(long id, String updatedTitle) {
        Optional<Todo> existingTodo = toDoRepository.findById(id);
        if (existingTodo.isEmpty()) {
            System.out.println("Todo Not Found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (existingTodo.get().getName().equals(updatedTitle)) {
                System.out.println("Existing Title is same as New Title");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                existingTodo.get().setName(updatedTitle);
                toDoRepository.save(existingTodo.get());
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
    }

    public ResponseEntity<Object> deleteTodo(long id) {
        Optional<Todo> todo = toDoRepository.findById(id);
        if (todo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        toDoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Object> updateTodoStatus(long id) {
        Optional<Todo> existingTodo = toDoRepository.findById(id);
        if (existingTodo.isEmpty()) {
            System.out.println("Todo Not Found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (existingTodo.get().getIsCompleted() == Boolean.TRUE) {
            System.out.println("Todo Already Completed");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        existingTodo.get().setIsCompleted(true);
        toDoRepository.save(existingTodo.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
