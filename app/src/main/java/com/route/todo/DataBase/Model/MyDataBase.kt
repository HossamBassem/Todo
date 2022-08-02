package com.route.todo.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.route.todo.DataBase.Dao.TodoDao
import com.route.todo.DataBase.Model.Todo
import com.route.todo.DataBase.Model.converters


@Database (entities = [Todo::class],version = 1)
@TypeConverters(converters::class)
abstract class MyDataBase: RoomDatabase() {
    abstract fun todoDao():TodoDao

    companion object{
        val DATABASE_NAME="todo-database"
                var myDataBase:MyDataBase?=null
                fun getInstance(context: Context):MyDataBase{
                    if (myDataBase==null){
                        myDataBase=Room.databaseBuilder(context,MyDataBase::class.java,
                            DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries() .build()


                    }
                return myDataBase !!}

    }
}