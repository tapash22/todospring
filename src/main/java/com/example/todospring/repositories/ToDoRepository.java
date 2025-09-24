package com.example.todospring.repositories;

import com.example.todospring.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<Todo,Long> {
}
