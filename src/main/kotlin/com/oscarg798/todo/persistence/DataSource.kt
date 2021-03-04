package com.oscarg798.todo.persistence

interface DataSource<T> {

    fun getClient(): T
}