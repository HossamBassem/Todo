package com.route.todo.DataBase.Model

import android.widget.ImageView
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
@Entity
data class Todo (
    @PrimaryKey(autoGenerate = true)

    val id:Int?=null,
    var name:String?=null,
    var detail:String?=null,

    @ColumnInfo
    var date: Date?=null,
    var isDone:Boolean?=false

        )

