package com.oscarg798.todo.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oscarg798.todo.presentation.viewmodel.ToDo

@Composable
fun ToDo(toDo: ToDo, onCheckChange: ToggleTodoListener, deleteClickListener: DeleteClickListener) {
    LazyRow(verticalAlignment = Alignment.CenterVertically) {
        item {
            Column(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, start = 8.dp)) {
                Checkbox(toDo.isCompleted, onCheckedChange = { onCheckChange(toDo) })
            }
            Column(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, start = 8.dp)) {
                Text(toDo.name)
            }

            Column(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, start = 8.dp)) {
                Button({ deleteClickListener(toDo) }) {
                    Text("Delete")
                }
            }
        }
    }
}