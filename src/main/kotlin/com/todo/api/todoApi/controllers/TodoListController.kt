package com.todo.api.todoApi.controllers

import com.todo.api.todoApi.entities.TodoList
import com.todo.api.todoApi.repositories.TodoListRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/items")
class TodoListController (private val todoListRepository: TodoListRepository){
    @GetMapping("/all")
    fun all() : Iterable<TodoList> {
        return todoListRepository.findByTodoListId(null)
    }

    @PostMapping("/add")
    fun add(@RequestParam(name = "title") title : String, @RequestParam(name = "todoListId") todoListId : Long?) : TodoList {
        return todoListRepository.save(TodoList(title = title, todoListId = todoListId))
    }
}