package com.oscarg798.todo.usecases

import com.oscarg798.todo.repository.ToDoRepository
import kotlinx.coroutines.flow.map

class GetToDosUseCase(private val toDoRepository: ToDoRepository) {

    suspend fun execute() = toDoRepository.getToDos().map {
        it.sortedBy { toDo ->
            toDo.isCompleted
        }
    }
}