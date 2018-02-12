package com.todo.api.todoApi.entities

import javax.persistence.*

@Entity
data class TodoList(
    val title: String = "",

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val todoListId: Long? = null,

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name="todoListId")
    var todoListItems: Collection<TodoList> = emptyList()
)