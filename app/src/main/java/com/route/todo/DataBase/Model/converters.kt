package com.route.todo.DataBase.Model

import androidx.room.TypeConverter
import java.util.*

class converters { @TypeConverter
fun toMilliSecond(date: Date?):Long{

    return date?.time ?: 0
}
    @TypeConverter
    fun fromMilliSecond(miliSeconds:Long): Date {
        return Date(miliSeconds)

    }
}