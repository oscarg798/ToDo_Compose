package com.oscarg798.todo.persistence

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class LocalDataSource : DataSource<Database> {

    private lateinit var db: Database


    override fun getClient(): Database  {
        if (::db.isInitialized) {
            return db
        }


        db = Database.connect(
            "jdbc:postgresql://localhost:5432/todo",
            driver = "org.postgresql.Driver",
            user = "oscar.gallon",
            password = ""
        )

        transaction {
            SchemaUtils.create(DBTodos)
        }

        return db
    }
}