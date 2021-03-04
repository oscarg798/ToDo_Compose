package com.oscarg798.todo.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.shortcuts
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private var state by mutableStateOf("")
typealias AddPressedListener = (String) -> Unit

@Composable
private fun AddLabel() {
    Text("ToDo Name")
}

@Composable
private fun AddTextField(onAddPressed: AddPressedListener) {
    TextField(value = state, label = { AddLabel() }, onValueChange = {
        state = it
    }, singleLine = true, modifier = Modifier.shortcuts {
        on(Key.Enter) {
            addTodo(onAddPressed)
        }
    }.fillMaxWidth())
}

@Composable
fun AddTodo(onAddPressed: AddPressedListener) {
    Box(modifier = Modifier.padding(8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(0.7f).wrapContentHeight()) {
                AddTextField(onAddPressed)
            }
            Column(
                modifier = Modifier.weight(0.3f)
            ) {
                Button(modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(16.dp), onClick = {
                    addTodo(onAddPressed)
                }) {
                    Text("Add")
                }

            }
        }
    }

}

private fun addTodo(addPressedListener: AddPressedListener) {
    addPressedListener(state)
    state = ""
}