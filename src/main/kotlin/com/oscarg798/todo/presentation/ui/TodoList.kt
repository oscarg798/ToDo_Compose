package com.oscarg798.todo.presentation.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.oscarg798.todo.presentation.viewmodel.ToDo
import com.oscarg798.todo.presentation.viewmodel.ViewState

typealias ToggleTodoListener = (ToDo) -> Unit
typealias DeleteClickListener = (ToDo) -> Unit

@Composable
fun TodoList(state: ViewState, toggleTodoListener: ToggleTodoListener, deleteClickListener: DeleteClickListener) {
    if (state.toDos.isEmpty()) {
        return
    }

    LazyColumn {
        items(items = state.toDos.toList()){ item ->
            ToDo(item, deleteClickListener = deleteClickListener, onCheckChange = toggleTodoListener)
        }
    }
}