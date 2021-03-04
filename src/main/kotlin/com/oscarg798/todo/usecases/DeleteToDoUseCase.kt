package com.oscarg798.todo.usecases

import com.oscarg798.todo.presentation.viewmodel.ToDo
import com.oscarg798.todo.repository.ToDoRepository

class DeleteToDoUseCase(private val toDoRepository: ToDoRepository) {

    suspend fun execute(toDo: ToDo){
        toDoRepository.delete(toDo)
        toDoRepository.refreshToDos()
    }
}