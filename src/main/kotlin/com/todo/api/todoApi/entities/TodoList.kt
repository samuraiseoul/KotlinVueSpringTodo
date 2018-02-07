package com.todo.api.todoApi.entities

import javax.persistence.*

@Entity
data class TodoList(
    val title: String = "",

    @Id @GeneratedValue
    val id: Long = 0,

    val todoListId: Long? = null,

    @OneToMany
    @JoinColumn(name="todoListId")
    var todoListItems: Collection<TodoList> = emptyList()
)