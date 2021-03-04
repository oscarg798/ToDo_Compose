package com.oscarg798.todo.usecases

import com.oscarg798.todo.presentation.viewmodel.ToDo
import com.oscarg798.todo.repository.ToDoRepository

class ToggleToDoUseCase(private val toDoRepository: ToDoRepository) {

    suspend fun execute(toDo: ToDo): ToDo {
        val updatedToDo = toDo.copy(isCompleted = !toDo.isCompleted)
        toDoRepository.toggle(updatedToDo)
        toDoRepository.refreshToDos()

        return updatedToDo
    }
}