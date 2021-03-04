package com.oscarg798.todo.persistence

import com.oscarg798.todo.presentation.viewmodel.ToDo
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import java.util.*

object DBTodos : UUIDTable() {
    val name: Column<String> = varchar("name", 200)
    val completed: Column<Boolean> = bool("completed")
}

class DBTodo(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<DBTodo>(DBTodos)

    var name by DBTodos.name
    var completed by DBTodos.completed

    fun toTodo() = ToDo(id.value.toString(), name, completed)
}