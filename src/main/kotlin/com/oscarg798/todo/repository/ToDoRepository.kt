package com.oscarg798.todo.repository

import com.oscarg798.todo.presentation.viewmodel.ToDo
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {

    suspend fun getToDos(): Flow<Collection<ToDo>>

    suspend fun toggle(toDo: ToDo)

    suspend fun addToDo(name: String): ToDo

    suspend fun refreshToDos()

    suspend fun delete(toDo: ToDo)
}