package com.example.todospring.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private Boolean isCompleted;
}
