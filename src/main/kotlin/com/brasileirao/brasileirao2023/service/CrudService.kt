package com.brasileirao.brasileirao2023.service

interface CrudService<T, ID> {

    fun findAll(): List<T>
    fun findBy(id: ID): T
    fun create(model: T): T
    fun update(id: ID, model: T) : T
    fun delete(id: ID)
}
