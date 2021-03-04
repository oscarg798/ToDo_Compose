package com.oscarg798.todo.persistence

import com.oscarg798.todo.presentation.viewmodel.ToDo
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.DEFAULT_ISOLATION_LEVEL
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.transactions.transactionManager
import java.util.*

class ExposeToDoDAO(private val dataSource: DataSource<Database>) : ToDoDAO {

    override suspend fun insert(name: String): ToDo {

        return newSuspendedTransaction(db = getDatabase()) {
            DBTodo.new {
                this.name = name
                completed = false
            }
        }.toTodo()
    }

    override suspend fun getAll(): Collection<ToDo> {
        return transaction (db = getDatabase()) {
            DBTodo.all()
        }.map {
            it.toTodo()
        }
    }

    override suspend fun update(toDo: ToDo) {
        newSuspendedTransaction(db = getDatabase()) {
            val dbToDo = DBTodo[UUID.fromString(toDo.id)]
            dbToDo.completed = !dbToDo.completed
            commit()
        }
    }

    override suspend fun delete(toDo: ToDo) {
        newSuspendedTransaction(db = getDatabase()) {
            val dbToDo = DBTodo[UUID.fromString(toDo.id)]
            dbToDo.delete()
        }
    }

    private fun getDatabase(): Database {
        val dataBase = dataSource.getClient()

        dataBase.transactionManager.newTransaction(DEFAULT_ISOLATION_LEVEL, null)


        return dataBase
    }
}