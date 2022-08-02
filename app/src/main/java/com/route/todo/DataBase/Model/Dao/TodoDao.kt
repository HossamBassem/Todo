package com.route.todo.DataBase.Dao

import androidx.room.*
import com.route.todo.DataBase.Model.Todo

@Dao
interface TodoDao {
    @Insert
    fun addTodo(todo: Todo)
    @Update
    fun updateTodo(todo: Todo)
@Delete
    fun deleteTodo(todo: Todo)
@Query("select * from Todo")
    fun getAllTodo():List<Todo>
}