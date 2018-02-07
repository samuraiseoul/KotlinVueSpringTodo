package com.todo.api.todoApi.repositories

import com.todo.api.todoApi.entities.TodoList
import org.springframework.data.repository.Repository

interface TodoListRepository : Repository<TodoList, Long> {
    fun findByTodoListId(id: Long?) : List<TodoList>
}