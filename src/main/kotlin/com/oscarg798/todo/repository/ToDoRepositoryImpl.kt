package com.oscarg798.todo.repository

import com.oscarg798.todo.persistence.ToDoDAO
import com.oscarg798.todo.presentation.viewmodel.ToDo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow

class ToDoRepositoryImpl(private val toDoDAO: ToDoDAO) : ToDoRepository {

    private val toDos = MutableStateFlow<Collection<ToDo>>(emptyList())

    override suspend fun getToDos(): Flow<Collection<ToDo>> = flow {
        emit(toDoDAO.getAll())
    }.flatMapMerge {
        toDos.value = it
        toDos
    }

    override suspend fun toggle(toDo: ToDo) {
        toDoDAO.update(toDo)
    }

    override suspend fun addToDo(name: String): ToDo {
        return toDoDAO.insert(name)
    }

    override suspend fun refreshToDos() {
        toDos.value = toDoDAO.getAll()
    }

    override suspend fun delete(toDo: ToDo) {
        toDoDAO.delete(toDo)
    }
}