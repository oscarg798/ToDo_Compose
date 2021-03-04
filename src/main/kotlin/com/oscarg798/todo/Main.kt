package com.oscarg798.todo

import androidx.compose.desktop.Window
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScrollableTabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oscarg798.todo.persistence.ExposeToDoDAO
import com.oscarg798.todo.persistence.LocalDataSource
import com.oscarg798.todo.presentation.AddTodo
import com.oscarg798.todo.presentation.Error
import com.oscarg798.todo.presentation.ui.TodoList
import com.oscarg798.todo.presentation.viewmodel.TodoViewModel
import com.oscarg798.todo.presentation.viewmodel.ViewState
import com.oscarg798.todo.repository.ToDoRepositoryImpl
import com.oscarg798.todo.usecases.AddToDoUseCase
import com.oscarg798.todo.usecases.DeleteToDoUseCase
import com.oscarg798.todo.usecases.GetToDosUseCase
import com.oscarg798.todo.usecases.ToggleToDoUseCase

fun main() {
    val dataSource = LocalDataSource()
    dataSource.getClient()
    val dao = ExposeToDoDAO(dataSource)
    val repository = ToDoRepositoryImpl(dao)
    val viewModel = TodoViewModel(
        addToDoUseCase = AddToDoUseCase(repository),
        getToDosUseCase = GetToDosUseCase(repository),
        toggleToDoUseCase = ToggleToDoUseCase(repository),
        deleteToDoUseCase = DeleteToDoUseCase(repository)
    )

    Window("com.oscarg798.todo.presentation.viewmodel.Todo App") {
        MaterialTheme {
            Content(viewModel)
        }
    }
}

@Composable
fun Content(viewModel: TodoViewModel) {
    val state by viewModel.state.collectAsState(ViewState())

    val stateVertical = rememberScrollState(0)
    Column(
        modifier = Modifier.fillMaxHeight().padding(16.dp)
            .scrollable(state = stateVertical, orientation = Orientation.Vertical, enabled = true)
    ) {
        Row(modifier = Modifier.weight(0.7f, true)) {
            TodoList(
                state,
                deleteClickListener = { viewModel.delete(it) },
                toggleTodoListener = { viewModel.toggleTodo(it) })
        }
        Row(modifier = Modifier.weight(0.2f, true).fillMaxWidth()) {
            AddTodo { viewModel.addTodo(it) }
        }
        Row(modifier = Modifier.weight(0.1f, true)) {
            Error(state)
        }

    }

}