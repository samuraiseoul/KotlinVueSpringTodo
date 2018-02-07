package com.todo.api.todoApi.controllers

import com.todo.api.todoApi.entities.TodoList
import com.todo.api.todoApi.repositories.TodoListRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/items")
class TodoListController (private val todoListRepository: TodoListRepository){
    @GetMapping("/all")
    fun all() : Iterable<TodoList> {
        return todoListRepository.findByTodoListId(null)
    }
}