package com.route.todo

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.route.todo.DataBase.MyDataBase
import java.util.*

class TodoList:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.todo_list,container,false)

    }
private lateinit var recyclerView: RecyclerView
private lateinit var calendarView: MaterialCalendarView
private lateinit var markAsDone:MaterialButton

 private val adapter= todoListAdapter(null, null)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initViews()


    }

    override fun onResume() {
        super.onResume()
        getTodosListFromDB()
    }
var calendar=Calendar.getInstance()


     fun getTodosListFromDB() {


        val todosList=MyDataBase.getInstance(requireContext()).todoDao().getTodosByDate(calendar.clearTime().time)
adapter.changeData(todosList.toMutableList(),todosList.toMutableList())
    }

    private fun initViews() {

        recyclerView=requireView().findViewById(R.id.recycler_view)
        calendarView=requireView().findViewById(R.id.calendarView)
        calendarView.selectedDate=CalendarDay.today()

      recyclerView.adapter=adapter

        calendarView.setOnDateChangedListener { widget, calendarDay, selected ->
            calendar.set(Calendar.DAY_OF_MONTH,calendarDay.day)
            calendar.set(Calendar.MONTH,calendarDay.month-1)
            calendar.set(Calendar.YEAR,calendarDay.year)
            getTodosListFromDB()


        }
    }
}