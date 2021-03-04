package com.oscarg798.todo.persistence

import com.oscarg798.todo.presentation.viewmodel.ToDo

interface ToDoDAO {

    suspend fun insert(name: String): ToDo

    suspend fun getAll(): Collection<ToDo>

    suspend fun update(toDo: ToDo)

    suspend fun delete(toDo: ToDo)


}