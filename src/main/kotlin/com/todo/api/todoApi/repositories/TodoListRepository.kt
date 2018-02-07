package com.todo.api.todoApi.repositories

import com.todo.api.todoApi.entities.TodoList
import org.springframework.data.repository.CrudRepository

interface TodoListRepository : CrudRepository<TodoList, Long> {
    fun findByTodoListId(id: Long?) : List<TodoList>
}