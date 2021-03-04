package com.oscarg798.todo.usecases

import com.oscarg798.todo.presentation.viewmodel.ToDo
import com.oscarg798.todo.repository.ToDoRepository

class AddToDoUseCase(private val toDoRepository: ToDoRepository) {

    suspend fun execute(name: String): ToDo {
        if (name == EMPTY_NAME) {
            throw IllegalArgumentException("ToDO name should not be empty")
        }

        if (name.length == NAME_MAX_ALLOWED_LENGTH) {
            throw IllegalArgumentException("ToDo name should not be longer than $NAME_MAX_ALLOWED_LENGTH")
        }

        val toDo = toDoRepository.addToDo(name)
        toDoRepository.refreshToDos()

        return toDo
    }
}

const val NAME_MAX_ALLOWED_LENGTH = 200
private const val EMPTY_NAME = ""