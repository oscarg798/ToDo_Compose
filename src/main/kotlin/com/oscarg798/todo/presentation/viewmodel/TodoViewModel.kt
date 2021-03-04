package com.oscarg798.todo.presentation.viewmodel

import com.oscarg798.todo.usecases.AddToDoUseCase
import com.oscarg798.todo.usecases.DeleteToDoUseCase
import com.oscarg798.todo.usecases.GetToDosUseCase
import com.oscarg798.todo.usecases.ToggleToDoUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn

class TodoViewModel(
    private val addToDoUseCase: AddToDoUseCase,
    private val getToDosUseCase: GetToDosUseCase,
    private val toggleToDoUseCase: ToggleToDoUseCase,
    private val deleteToDoUseCase: DeleteToDoUseCase
) {

    private val supervisorJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(supervisorJob)

    private val _state = MutableStateFlow(ViewState())
    private val currentState
        get() = _state.value

    val state: Flow<ViewState> = _state

    init {
        getTodos()
    }

    private fun getTodos() {
        viewModelScope.launch {
            getToDosUseCase.execute().flowOn(Dispatchers.IO)
                .collect { toDos ->
                    updateState(currentState.copy(toDos = toDos, loading = false, error = null))
                }
        }
    }

    fun addTodo(name: String) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    addToDoUseCase.execute(name)
                }
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    fun toggleTodo(toDo: ToDo) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                toggleToDoUseCase.execute(toDo)
            }
        }
    }

    fun delete(toDo: ToDo) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                deleteToDoUseCase.execute(toDo)
            }
        }
    }

    private fun onError(error: Throwable) {
        if (error !is Exception) throw error
        updateState(currentState.copy(error = error.message, loading = false))
    }

    private fun updateState(state: ViewState) {
        _state.value = state
    }
}

data class ViewState(
    val loading: Boolean = false,
    val toDos: Collection<ToDo> = emptyList(),
    val error: String? = null
)

data class ToDo(val id: String, val name: String, val isCompleted: Boolean = false)
