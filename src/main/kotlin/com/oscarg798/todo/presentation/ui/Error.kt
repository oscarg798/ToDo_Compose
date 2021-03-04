package com.oscarg798.todo.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.oscarg798.todo.presentation.viewmodel.ViewState

@Composable
fun Error(state: ViewState) {

    if (state.error == null) {
        return
    }

    Row {
        Text(state.error)
    }

}
